package com.sublime.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movies")
data class WatchedEntity(
    @PrimaryKey
    val movieId: Long,
    val watchedAt: Long
)