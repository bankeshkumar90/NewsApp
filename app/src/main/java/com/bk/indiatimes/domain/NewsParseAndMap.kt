package com.bk.indiatimes.domain

import com.bk.indiatimes.data.local.NewsItemEntity
import com.bk.indiatimes.data.model.NewsItem
import javax.inject.Inject

class NewsParseAndMap @Inject constructor() {

    fun map(input: NewsItemEntity): NewsItem {
        return NewsItem(
            author = input.author,
            content = input.content,
            date = input.date,
            imageUrl = input.imageUrl,
            readMoreUrl = input.readMoreUrl.orEmpty(),
            time = input.time,
            title = input.title,
            url = input.url
        )
    }

    fun map(input: NewsItem, category: String): NewsItemEntity {
        return NewsItemEntity(
            author = input.author,
            content = input.content,
            date = input.date,
            imageUrl = input.imageUrl,
            readMoreUrl = input.readMoreUrl.orEmpty(),
            time = input.time,
            title = input.title,
            url = input.url,
            category = category
        )
    }
}