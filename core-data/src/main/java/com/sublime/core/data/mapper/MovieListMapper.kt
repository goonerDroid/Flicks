package com.sublime.core.data.mapper

import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.model.Movie

fun List<MovieEntity>.asExternalModels(): List<Movie> {
    return map { it.asExternalModel() }
}