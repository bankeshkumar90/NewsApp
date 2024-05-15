package com.bk.indiatimes.data.model

data class NewsResponseModel(
    val category: String,
    val data: List<NewsItem>,
    val error: String? = null,
    val success: Boolean
)

data class NewsItem(
    val author: String,
    val content: String,
    val date: String,
    val imageUrl: String,
    val readMoreUrl: String?,
    val time: String,
    val title: String,
    val url: String
)