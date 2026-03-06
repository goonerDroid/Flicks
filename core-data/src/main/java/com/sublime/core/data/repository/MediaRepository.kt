package com.sublime.core.data.repository

import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Media
import com.sublime.core.model.MediaType
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    fun observeMedia(
        category: BrowseCategory,
        mediaType: MediaType
    ): Flow<List<Media>>

    suspend fun syncMedia(
        category: BrowseCategory,
        mediaType: MediaType
    )
}