package com.sublime.core.data.mapper

import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.model.Movie

fun MovieEntity.asExternalModel(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview ?: "",
        rating = voteAverage
    )
}