package com.sublime.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sublime.core.model.BrowseCategory

@Entity(
    tableName = "movies",
    indices = [
        Index(value = ["category"])
    ]
)
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val popularity: Double,
    val voteAverage: Double,
    val category: BrowseCategory
)