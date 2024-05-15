package com.bk.indiatimes.data.network

import com.bk.indiatimes.data.model.NewsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/news")
    suspend fun getNews(
        @Query("category") category: String
    ): Response<NewsResponseModel>
}
