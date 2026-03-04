package com.sublime.core.model

import java.time.Instant

data class UserMovieInteraction(
    val userId: Long,
    val movieId: Int,
    val watchStatus: WatchStatus,
    val rating: Int?,
    val addedToWatchlistAt: Instant?,
    val watchedAt: Instant?
)