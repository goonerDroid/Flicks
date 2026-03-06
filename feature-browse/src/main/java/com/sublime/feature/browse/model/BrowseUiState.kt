package com.sublime.feature.browse.model

sealed interface BrowseUiState {

    data object Loading : BrowseUiState

    data class Success(
        val sections: List<BrowseSection>
    ) : BrowseUiState

    data class Error(
        val throwable: Throwable
    ) : BrowseUiState
}