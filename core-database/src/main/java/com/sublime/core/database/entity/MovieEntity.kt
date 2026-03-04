package com.sublime.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sublime.core.database.model.MovieCategory

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val popularity: Double,
    val voteAverage: Double,
    val category: MovieCategory
)