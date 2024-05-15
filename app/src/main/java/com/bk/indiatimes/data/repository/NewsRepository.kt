package com.bk.indiatimes.data.repository

import android.content.Context
import com.bk.indiatimes.R
import com.bk.indiatimes.data.local.NewsItemDao
import com.bk.indiatimes.data.model.NewsItem
import com.bk.indiatimes.data.network.Api
import com.bk.indiatimes.domain.NewsDataSource
import com.bk.indiatimes.domain.NewsParseAndMap
import com.bk.indiatimes.utils.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private var api: Api,
    private var dao: NewsItemDao,
    private val parseAndMap: NewsParseAndMap,
    @ApplicationContext private val context: Context
) : NewsDataSource {

    override fun getNews(category: String): Flow<Resource<List<NewsItem>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.getNews(category = category)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                if (!result.success) {
                    emit(Resource.Error(message = result.error ?: context.getString(R.string.something_went_wrong)))
                }
                dao.insertNewsItems(
                    result.data.map {
                        parseAndMap.map(it, category)
                    }
                )
            } else {
                emit(Resource.Error(message = response.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = context.getString(R.string.couldnt_reach_server)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: context.getString(R.string.something_went_wrong)))
        }

        val newLocalNews = dao.getNewsItems(category).map { parseAndMap.map(it) }
        emit(Resource.Success(data = newLocalNews))
    }
}