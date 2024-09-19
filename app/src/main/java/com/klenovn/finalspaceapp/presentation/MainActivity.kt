package com.klenovn.finalspaceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.klenovn.finalspaceapp.presentation.navigation.NavGraph
import com.klenovn.finalspaceapp.presentation.ui.theme.FinalSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalSpaceAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}