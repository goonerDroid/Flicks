package com.sublime.core.network.datasource

import com.sublime.core.model.MovieCategory
import com.sublime.core.model.TvCategory
import com.sublime.core.network.model.NetworkMovie
import com.sublime.core.network.model.NetworkPagedResponse
import com.sublime.core.network.model.NetworkTvShow

public interface TmdbNetworkDataSource {

    suspend fun getMovies(
        category: MovieCategory,
        page: Int
    ): NetworkPagedResponse<NetworkMovie>

    suspend fun getTvShows(
        category: TvCategory,
        page: Int
    ): NetworkPagedResponse<NetworkTvShow>

}