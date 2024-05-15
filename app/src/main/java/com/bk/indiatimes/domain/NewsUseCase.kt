package com.bk.indiatimes.domain

import com.bk.indiatimes.data.model.NewsItem
import com.bk.indiatimes.data.repository.NewsRepository
import com.bk.indiatimes.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(category: String): Flow<Resource<List<NewsItem>>> =
        repository.getNews(category = category)
}
