package com.sublime.feature.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sublime.core.data.repository.BrowseRepository
import com.sublime.core.model.BrowseCategory
import com.sublime.core.model.MediaType
import com.sublime.feature.browse.model.BrowseSection
import com.sublime.feature.browse.model.BrowseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val repository: BrowseRepository
) : ViewModel() {

    private val popular =
        repository.observeMedia(
            category = BrowseCategory.POPULAR,
            mediaType = MediaType.MOVIE
        )

    private val topRated =
        repository.observeMedia(
            category = BrowseCategory.TOP_RATED,
            mediaType = MediaType.MOVIE
        )

    private val upcoming =
        repository.observeMedia(
            category = BrowseCategory.UPCOMING,
            mediaType = MediaType.MOVIE
        )

    private val nowPlaying =
        repository.observeMedia(
            category = BrowseCategory.NOW_PLAYING,
            mediaType = MediaType.MOVIE
        )

    val uiState: StateFlow<BrowseUiState> =
        combine(
            popular,
            topRated,
            upcoming,
            nowPlaying
        ) { popularMovies, topRatedMovies, upcomingMovies, nowPlayingMovies ->

            BrowseUiState.Success(
                sections = listOf(
                    BrowseSection(
                        category = BrowseCategory.NOW_PLAYING,
                        media = nowPlayingMovies
                    ),
                    BrowseSection(
                        category = BrowseCategory.POPULAR,
                        media = popularMovies
                    ),
                    BrowseSection(
                        category = BrowseCategory.TOP_RATED,
                        media = topRatedMovies
                    ),
                    BrowseSection(
                        category = BrowseCategory.UPCOMING,
                        media = upcomingMovies
                    )
                )
            )
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

            repository.syncMedia(BrowseCategory.POPULAR, MediaType.MOVIE)
            repository.syncMedia(BrowseCategory.TOP_RATED, MediaType.MOVIE)
            repository.syncMedia(BrowseCategory.UPCOMING, MediaType.MOVIE)
            repository.syncMedia(BrowseCategory.NOW_PLAYING, MediaType.MOVIE)

        }
    }
}