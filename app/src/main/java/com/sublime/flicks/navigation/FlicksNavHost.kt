package com.sublime.flicks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sublime.feature.browse.BrowseScreen

@Composable
fun FlicksNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "browse"
    ) {

        composable("browse") {
            BrowseScreen()
        }

    }
}