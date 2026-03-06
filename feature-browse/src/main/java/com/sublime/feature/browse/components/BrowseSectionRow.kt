package com.sublime.feature.browse.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sublime.feature.browse.model.BrowseSection

@Composable
fun BrowseSectionRow(
    section: BrowseSection
) {

    Column {
        Text(
            text = section.category.name,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(
                items = section.media,
                key = { it.id }
            ) { media ->
                MediaPoster(media)
            }
        }
    }
}