package com.bk.indiatimes.di

import android.content.Context
import com.bk.indiatimes.data.local.NewsItemDatabase
import com.bk.indiatimes.data.network.Api
import com.bk.indiatimes.data.repository.NewsRepository
import com.bk.indiatimes.domain.NewsParseAndMap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: Api,
        db: NewsItemDatabase,
        mapper: NewsParseAndMap,
        @ApplicationContext context: Context
    ): NewsRepository = NewsRepository(
        api,
        db.dao,
        mapper,
        context
    )
}