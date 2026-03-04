package com.sublime.core.network.model

import com.squareup.moshi.JsonClass

@Suppress("PropertyName")
@JsonClass(generateAdapter = true)
data class NetworkPagedResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)