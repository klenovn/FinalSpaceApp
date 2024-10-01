package com.klenovn.finalspaceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.klenovn.finalspaceapp.presentation.navigation.BottomNavItem
import com.klenovn.finalspaceapp.presentation.navigation.BottomNavigationBar
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetail
import com.klenovn.finalspaceapp.presentation.navigation.CharactersRoute
import com.klenovn.finalspaceapp.presentation.navigation.NavGraph
import com.klenovn.finalspaceapp.presentation.ui.theme.FinalSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            FinalSpaceAppTheme {
                val currentDestination = navBackStackEntry?.destination?.route
                val shouldDisplayBottomBar = when (currentDestination) {
                    in bottomNavItems.map { it.route::class.qualifiedName } -> true
                    else -> false
                }

                Scaffold(bottomBar = {
                    if (shouldDisplayBottomBar) BottomNavigationBar(
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