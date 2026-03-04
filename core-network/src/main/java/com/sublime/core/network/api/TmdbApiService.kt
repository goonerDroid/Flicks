package com.sublime.core.network.api

import com.sublime.core.network.model.NetworkMovie
import com.sublime.core.network.model.NetworkPagedResponse
import com.sublime.core.network.model.NetworkTvShow
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    // Movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkMovie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkMovie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkMovie>


    // TV Shows
    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkTvShow>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkTvShow>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkTvShow>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int
    ): NetworkPagedResponse<NetworkTvShow>
}