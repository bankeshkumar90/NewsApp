package com.bk.indiatimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.bk.indiatimes.presentation.appnavigation.Navigation
import com.bk.indiatimes.presentation.screen.NewsDetailsScreen
import com.bk.indiatimes.ui.theme.IndiaTimesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {
            IndiaTimesTheme {
                Navigation()
                //NewsDetailsScreen(hiltViewModel(), navController)
            }
        }
    }
}
