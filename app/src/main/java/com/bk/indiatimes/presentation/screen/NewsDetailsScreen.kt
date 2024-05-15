package com.bk.indiatimes.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bk.indiatimes.data.local.Categories
import com.bk.indiatimes.presentation.appnavigation.Screen
import com.bk.indiatimes.presentation.component.NewsCard
import com.bk.indiatimes.presentation.component.NewsTopAppBar
import com.bk.indiatimes.presentation.screen.viewmodel.NewsViewModel
import com.bk.indiatimes.utils.ObserveAsEvents
import com.bk.indiatimes.utils.showToast
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsContent(
    state: NewsScreenState,
    onEvent: (NewsDetailsScreenEvent) -> Unit,
    onClickCategory: (category: String) -> Unit,
    onOpenNewsDetails: (url: String) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val listState = rememberLazyListState()
    val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val isSelected = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = firstVisibleItemIndex) {
        onEvent(NewsDetailsScreenEvent.UpdateScrollPosition(firstVisibleItemIndex))
    }
    Scaffold(topBar = {
        NewsTopAppBar(
            scrollBehavior = scrollBehavior,
            scrollUpState = state.isScrollUp
        )
    }) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        Box {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(top = 8.dp),
                state = listState
            ) {
                item {
                    LazyRow {
                        items(Categories.categoriesList) { category ->
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 4.dp)
                                    .clickable {
                                        onClickCategory.invoke(category.value)
                                        isSelected.value =
                                            Categories.categoriesList.indexOf(category)
                                    },
                                elevation = CardDefaults.cardElevation(2.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected.value == Categories.categoriesList.indexOf(
                                            category
                                        )
                                    )
                                        Color.Gray else Color.White
                                ),
                                shape = RoundedCornerShape(4.dp),
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = category.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                items(state.newsItems) { news ->
                    NewsCard(newsItem = news,
                        onOpenDetailNews = {
                            onOpenNewsDetails(it)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun NewsDetailsScreen(viewModel: NewsViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { uiEvent ->
        when (uiEvent) {
            is NewsViewModel.UIEvent.ShowError -> {
                context.showToast(uiEvent.message)
            }
        }
    }


    NewsDetailsContent(
        state = state,
        onEvent = viewModel::onEvent,
        onClickCategory = {
            viewModel.getNews(it)
        },
        onOpenNewsDetails = {
            val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
            navController.navigate(Screen.WebLinkScreen.withArgs(encodedUrl))
        }
    )
}


@Preview
@Composable
fun NewsDetailsScreenPreview(
    @PreviewParameter(NewsScreenStatePreviewParameterProvider::class) state: NewsScreenState
) {
    NewsDetailsContent(state = state, onEvent = {}, {}, {})
}
