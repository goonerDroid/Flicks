package com.sublime.feature.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sublime.core.data.repository.MediaRepository
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.MediaType
import com.sublime.feature.browse.model.BrowseSection
import com.sublime.feature.browse.model.BrowseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val repository: MediaRepository
) : ViewModel() {


    private val categories = listOf(
        BrowseCategory.NOW_PLAYING,
        BrowseCategory.POPULAR,
        BrowseCategory.TOP_RATED,
        BrowseCategory.UPCOMING
    )

    private val flows = categories.map { category ->
        repository.observeMedia(
            category = category,
            mediaType = MediaType.MOVIE
        )
    }

    val uiState: StateFlow<BrowseUiState> =
        combine(flows) { mediaLists ->

            val sections = categories.zip(mediaLists).map { (category, media) ->
                BrowseSection(
                    category = category,
                    media = media
                )
            }

            BrowseUiState.Success(sections) as BrowseUiState
        }
            .catch {
                emit(BrowseUiState.Error("Failed to load movies"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BrowseUiState.Loading
            )

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            coroutineScope {
                launch { repository.syncMedia(BrowseCategory.POPULAR, MediaType.MOVIE) }
                launch { repository.syncMedia(BrowseCategory.TOP_RATED, MediaType.MOVIE) }
                launch { repository.syncMedia(BrowseCategory.UPCOMING, MediaType.MOVIE) }
                launch { repository.syncMedia(BrowseCategory.NOW_PLAYING, MediaType.MOVIE) }
            }
        }
    }
}