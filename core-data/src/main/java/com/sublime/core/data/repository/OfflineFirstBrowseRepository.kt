package com.sublime.core.data.repository

import com.sublime.core.data.repository.movies.MovieRepository
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Media
import com.sublime.core.model.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstBrowseRepository @Inject constructor(
    private val movieRepository: MovieRepository,
) : BrowseRepository {

    override fun observeMedia(
        category: BrowseCategory,
        mediaType: MediaType
    ): Flow<List<Media>> {

        return when (mediaType) {

            MediaType.MOVIE ->
                movieRepository.observeMovies(category)

            MediaType.TV ->
                movieRepository.observeMovies(category)//TODO this will change to tvrepository
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
                movieRepository.observeMovies(category) //TODO this will change to tvrepository
        }
    }
}