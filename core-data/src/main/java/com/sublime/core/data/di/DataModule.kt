package com.sublime.core.data.di

import com.sublime.core.data.repository.BrowseRepository
import com.sublime.core.data.repository.OfflineFirstBrowseRepository
import com.sublime.core.data.repository.movies.MovieRepository
import com.sublime.core.data.repository.movies.OfflineFirstMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(
        impl: OfflineFirstMovieRepository
    ): MovieRepository

    @Binds
    abstract fun bindBrowseRepository(
        impl: OfflineFirstBrowseRepository
    ): BrowseRepository
}