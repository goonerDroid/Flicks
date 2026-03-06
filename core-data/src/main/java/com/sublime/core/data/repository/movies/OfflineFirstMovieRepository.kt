package com.sublime.core.data.repository.movies

import android.util.Log
import com.sublime.core.data.di.IoDispatcher
import com.sublime.core.data.mapper.asEntity
import com.sublime.core.data.mapper.asExternalModel
import com.sublime.core.database.dao.MovieDao
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Movie
import com.sublime.core.network.datasource.TmdbNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineFirstMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val network: TmdbNetworkDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override fun observeMovies(
        category: BrowseCategory
    ): Flow<List<Movie>> {

        return movieDao.observeMoviesByCategory(
            category
        )
            .map { entities ->
                entities.map { it.asExternalModel() }
            }
    }

    override suspend fun syncMovies(category: BrowseCategory) {
        withContext(ioDispatcher) {
            try {
                val response = network.getMovies(
                    category = category,
                    page = 1
                )

                val entities = response.results.map {
                    it.asEntity(category)
                }

                movieDao.replaceMovies(
                    category = category,
                    movies = entities
                )

            } catch (e: IOException) {
                Log.e(
                    OfflineFirstMovieRepository::class.java.simpleName,
                    "Network error while syncing movies",
                    e
                )
            } catch (e: HttpException) {
                Log.e(
                    OfflineFirstMovieRepository::class.java.simpleName,
                    "API error while syncing movies",
                    e
                )
            }
        }
    }
}