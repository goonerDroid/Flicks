package com.sublime.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkTvShow(

    val id: Int,

    val name: String,

    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "first_air_date")
    val firstAirDate: String?,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Int,

    val popularity: Double
)