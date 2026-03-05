package com.sublime.core.data.repository.movies

import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun observeMovies(
        category: BrowseCategory
    ): Flow<List<Movie>>
}