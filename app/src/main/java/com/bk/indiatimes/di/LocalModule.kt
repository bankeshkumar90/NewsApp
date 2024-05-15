package com.bk.indiatimes.di

import android.app.Application
import androidx.room.Room
import com.bk.indiatimes.data.local.NewsItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideNewsItemDao(
        app: Application
    ): NewsItemDatabase {
        return Room.databaseBuilder(
            app,
            NewsItemDatabase::class.java,
            "news_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
