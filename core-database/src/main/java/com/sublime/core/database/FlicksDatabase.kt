package com.sublime.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sublime.core.database.converter.MovieCategoryConverter
import com.sublime.core.database.dao.MovieDao
import com.sublime.core.database.dao.WatchedDao
import com.sublime.core.database.dao.WatchlistDao
import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.database.entity.WatchedEntity
import com.sublime.core.database.entity.WatchlistEntity

@Database(
    entities = [
        MovieEntity::class,
        WatchlistEntity::class,
        WatchedEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieCategoryConverter::class)
abstract class FlicksDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun watchlistDao(): WatchlistDao
    abstract fun watchedDao(): WatchedDao
}