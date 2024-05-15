package com.bk.indiatimes.di

import com.bk.indiatimes.data.repository.NewsRepository
import com.bk.indiatimes.domain.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        repository: NewsRepository
    ): NewsUseCase = NewsUseCase(repository)
}
