package com.sublime.core.data.repository

import com.sublime.core.data.repository.movies.MovieRepository
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Media
import com.sublime.core.model.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class OfflineFirstMediaRepository @Inject constructor(
    private val movieRepository: MovieRepository,
) : MediaRepository {

    override fun observeMedia(
        category: BrowseCategory,
        mediaType: MediaType
    ): Flow<List<Media>> {

        return when (mediaType) {

            MediaType.MOVIE ->
                movieRepository.observeMovies(category)

            MediaType.TV ->
                emptyFlow() // TV support later
        }
    }

    override suspend fun syncMedia(
        category: BrowseCategory,
        mediaType: MediaType
    ) {

        when (mediaType) {

            MediaType.MOVIE ->
                movieRepository.syncMovies(category)

            MediaType.TV ->
                Unit // TV support later
        }
    }
}