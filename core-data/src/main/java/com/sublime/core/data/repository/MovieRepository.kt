package com.sublime.core.data.repository

import com.sublime.core.model.Movie
import com.sublime.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun observeMovies(
        category: MovieCategory
    ): Flow<List<Movie>>

    suspend fun fetchMovies(
        category: MovieCategory,
        page: Int
    )
}