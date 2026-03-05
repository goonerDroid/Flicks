package com.sublime.core.data.repository

import com.sublime.core.data.mapper.asEntity
import com.sublime.core.data.mapper.asExternalModel
import com.sublime.core.database.dao.MovieDao
import com.sublime.core.model.Movie
import com.sublime.core.model.MovieCategory
import com.sublime.core.network.datasource.TmdbNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val network: TmdbNetworkDataSource
) : MovieRepository {

    override fun observeMovies(
        category: MovieCategory
    ): Flow<List<Movie>> {
        return movieDao
            .observeMoviesByCategory(category)
            .map { entities ->
                entities.map { it.asExternalModel() }
            }
    }

    override suspend fun fetchMovies(
        category: MovieCategory,
        page: Int
    ) {
        val response = network.getMovies(category, page)

        val entities = response.results.map {
            it.asEntity(category)
        }

        movieDao.insertMovies(entities)
    }
}