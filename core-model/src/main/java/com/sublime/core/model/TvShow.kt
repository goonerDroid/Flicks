package com.sublime.core.model

data class TvShow(
    val id: Int,
    val name: String,
    val overview: String,
    val posterUrl: String?,
    val firstAirDate: String?
)