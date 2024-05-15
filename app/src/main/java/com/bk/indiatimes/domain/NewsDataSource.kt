package com.bk.indiatimes.domain

import com.bk.indiatimes.data.model.NewsItem
import com.bk.indiatimes.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    fun getNews(category: String): Flow<Resource<List<NewsItem>>>
}
