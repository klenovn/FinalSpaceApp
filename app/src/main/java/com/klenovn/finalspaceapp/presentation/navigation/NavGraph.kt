package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klenovn.finalspaceapp.presentation.characters.CharactersScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(modifier = modifier, navController = navController, startDestination = CharactersRoute) {

        composable<CharactersRoute> {
            CharactersScreen(navController = navController)
        }
    }
}