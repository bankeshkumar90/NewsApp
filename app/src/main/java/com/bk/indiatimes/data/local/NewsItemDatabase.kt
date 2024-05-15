package com.bk.indiatimes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsItemEntity::class],
    version = 1
)
abstract class NewsItemDatabase : RoomDatabase() {

    abstract val dao: NewsItemDao
}
