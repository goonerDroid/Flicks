package com.sublime.core.data.mapper

import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.MovieCategory
import com.sublime.core.network.model.NetworkMovie

fun NetworkMovie.asEntity(
    category: BrowseCategory
): MovieEntity {

    return MovieEntity(
        id = id.toLong(),
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        category = category.asMovieCategory()
    )
}

fun BrowseCategory.asMovieCategory(): MovieCategory {
    return when (this) {
        BrowseCategory.POPULAR -> MovieCategory.POPULAR
        BrowseCategory.TOP_RATED -> MovieCategory.TOP_RATED
        BrowseCategory.UPCOMING -> MovieCategory.UPCOMING
        BrowseCategory.NOW_PLAYING -> MovieCategory.NOW_PLAYING
    }
}