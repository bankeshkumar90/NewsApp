package com.bk.indiatimes.presentation.appnavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bk.indiatimes.presentation.screen.MoreDetailNews
import com.bk.indiatimes.presentation.screen.NewsDetailsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsDetails.route) {
        composable(route = Screen.NewsDetails.route) {
            NewsDetailsScreen(hiltViewModel(), navController)
        }
        composable(
            route = Screen.WebLinkScreen.route + "/{args}",
            arguments = listOf(
                navArgument(name = "args") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            val args = backStackEntry.arguments?.getString("args")
            MoreDetailNews( navController , args)
        }
    }
}