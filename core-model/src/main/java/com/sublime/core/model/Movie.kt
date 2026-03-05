package com.sublime.core.model

data class Movie(
    override val id: Long,
    override val title: String,
    override val posterPath: String?,
    override val overview: String,
    override val rating: Double
) : Media {

    override val mediaType = MediaType.MOVIE
}