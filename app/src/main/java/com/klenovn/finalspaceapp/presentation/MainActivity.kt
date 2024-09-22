package com.klenovn.finalspaceapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.klenovn.finalspaceapp.presentation.navigation.BottomNavItem
import com.klenovn.finalspaceapp.presentation.navigation.BottomNavigationBar
import com.klenovn.finalspaceapp.presentation.navigation.NavGraph
import com.klenovn.finalspaceapp.presentation.ui.theme.FinalSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomNavItems = listOf(
                BottomNavItem.Characters,
                BottomNavItem.Favourites
            )

            val selectedItemIndex = rememberSaveable {
                mutableIntStateOf(0)
            }

            FinalSpaceAppTheme {
                val navController = rememberNavController()

                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        items = bottomNavItems,
                        selectedItemIndex = selectedItemIndex
                    )
                }) { innerPaddings ->
                    NavGraph(navController = navController, modifier = Modifier.padding(innerPaddings))
                }
            }
        }
    }
}