package com.sublime.core.data.mapper

import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.model.Movie

fun MovieEntity.asExternalModel(): Movie {
    return Movie(
        id = id.toInt(),
        title = title,
        overview = overview ?: "",
        posterUrl = posterPath,
        releaseDate = releaseDate
    )
}