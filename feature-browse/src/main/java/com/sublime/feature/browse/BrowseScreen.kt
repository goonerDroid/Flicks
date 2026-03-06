package com.sublime.feature.browse

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sublime.feature.browse.components.BrowseSectionRow
import com.sublime.feature.browse.model.BrowseUiState

@Composable
fun BrowseScreen(
    viewModel: BrowseViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {

        BrowseUiState.Loading -> {
            CircularProgressIndicator()
        }

        is BrowseUiState.Success -> {

            val sections =
                (uiState as BrowseUiState.Success).sections

            LazyColumn {

                items(sections) { section ->

                    BrowseSectionRow(section)

                }
            }
        }

        is BrowseUiState.Error -> {
            Text("Something went wrong")
        }
    }
}