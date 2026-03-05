package com.sublime.core.model

sealed interface Media {
    val id: Long
    val title: String
    val posterPath: String?
    val overview: String
    val rating: Double
    val mediaType: MediaType
}