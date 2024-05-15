package com.bk.indiatimes.presentation.appnavigation

sealed class Screen(val route: String) {
    data object WebLinkScreen : Screen("weblink_screen")
    data object NewsDetails : Screen("news_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}