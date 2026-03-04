package com.sublime.core.network.datasource

import com.sublime.core.model.MovieCategory
import com.sublime.core.model.TvCategory
import com.sublime.core.network.api.TmdbApiService
import com.sublime.core.network.model.NetworkMovie
import com.sublime.core.network.model.NetworkPagedResponse
import com.sublime.core.network.model.NetworkTvShow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitTmdbNetworkDataSource @Inject constructor(
    private val api: TmdbApiService
) : TmdbNetworkDataSource {

    override suspend fun getMovies(
        category: MovieCategory,
        page: Int
    ): NetworkPagedResponse<NetworkMovie> {

        return when (category) {
            MovieCategory.POPULAR -> api.getPopularMovies(page)

            MovieCategory.TOP_RATED -> api.getTopRatedMovies(page)

            MovieCategory.UPCOMING -> api.getUpcomingMovies(page)

            MovieCategory.NOW_PLAYING -> api.getNowPlayingMovies(page)
        }
    }

    override suspend fun getTvShows(
        category: TvCategory,
        page: Int
    ): NetworkPagedResponse<NetworkTvShow> {

        return when (category) {
            TvCategory.POPULAR -> api.getPopularTvShows(page)

            TvCategory.TOP_RATED -> api.getTopRatedTvShows(page)

            TvCategory.ON_THE_AIR -> api.getOnTheAirTvShows(page)

            TvCategory.AIRING_TODAY -> api.getAiringTodayTvShows(page)
        }
    }
}