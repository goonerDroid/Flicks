package com.sublime.core.database.di

import android.content.Context
import androidx.room.Room
import com.sublime.core.database.FlicksDatabase
import com.sublime.core.database.dao.MovieDao
import com.sublime.core.database.dao.WatchedDao
import com.sublime.core.database.dao.WatchlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FlicksDatabase {

        return Room.databaseBuilder(
            context,
            FlicksDatabase::class.java,
            "flicks_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(
        database: FlicksDatabase
    ): MovieDao = database.movieDao()

    @Provides
    fun provideWatchlistDao(
        database: FlicksDatabase
    ): WatchlistDao = database.watchlistDao()

    @Provides
    fun provideWatchedDao(
        database: FlicksDatabase
    ): WatchedDao = database.watchedDao()
}