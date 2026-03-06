package com.sublime.feature.browse.model

import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.Media

data class BrowseSection(
    val category: BrowseCategory,
    val media: List<Media>
)