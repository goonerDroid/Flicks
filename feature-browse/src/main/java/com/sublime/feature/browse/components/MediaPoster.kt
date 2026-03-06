package com.sublime.feature.browse.components

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.sublime.core.model.Media

@Composable
fun MediaPoster(
    media: Media
) {

    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${media.posterPath}",
        contentDescription = media.title
    )
}