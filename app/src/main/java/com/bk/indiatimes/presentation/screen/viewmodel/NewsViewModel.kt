package com.bk.indiatimes.presentation.screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bk.indiatimes.data.local.Categories
import com.bk.indiatimes.domain.NewsUseCase
import com.bk.indiatimes.presentation.screen.NewsDetailsScreenEvent
import com.bk.indiatimes.presentation.screen.NewsScreenState
import com.bk.indiatimes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: NewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(NewsScreenState())
    val uiState = _uiState.asStateFlow()
    private val categoryList = Categories.categoriesList
    init {
        if(!categoryList.isEmpty())
            getNews(getCategoryOfNews(0))
    }

    private fun getCategoryOfNews(index:Int):String{
        return categoryList[index].value
    }

    private var lastScrollIndex = 0
    fun onEvent(event: NewsDetailsScreenEvent) {
        when (event) {
            is NewsDetailsScreenEvent.UpdateScrollPosition -> {
                if (event.position == lastScrollIndex) return
                _uiState.update { state ->
                    state.copy(
                        isScrollUp = event.position > lastScrollIndex
                    )
                }
                lastScrollIndex = event.position
            }
        }
    }

    fun getNews(category: String) = viewModelScope.launch {
        getNewsUseCase.invoke(category = category).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            categoryName = category,
                            newsItems = result.data,
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false
                        )
                    }
                    _uiEvent.trySend(UIEvent.ShowError(message = result.message))
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowError(val message: String) : UIEvent()
    }
}
