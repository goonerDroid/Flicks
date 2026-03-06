package com.sublime.core.data.repository.movies

import com.sublime.core.data.mapper.asEntity
import com.sublime.core.data.mapper.asExternalModel
import com.sublime.core.data.mapper.asMovieCategory
import com.sublime.core.database.dao.MovieDao
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Movie
import com.sublime.core.network.datasource.TmdbNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineFirstMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val network: TmdbNetworkDataSource
) : MovieRepository {

    override fun observeMovies(
        category: BrowseCategory
    ): Flow<List<Movie>> {

        return movieDao.observeMoviesByCategory(
            category.asMovieCategory()
        )
            .map { entities ->
                entities.map { it.asExternalModel() }
            }
    }

    override suspend fun syncMovies(category: BrowseCategory) {
        val response = network.getMovies(
            category = category.asMovieCategory(),
            page = 1
        )

        val entities = response.results.map {
            it.asEntity(category)
        }

        movieDao.insertMovies(entities)
    }
}