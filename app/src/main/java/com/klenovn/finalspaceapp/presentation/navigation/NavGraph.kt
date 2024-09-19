package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klenovn.finalspaceapp.presentation.characters.CharactersScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = CharactersRoute) {

        composable<CharactersRoute> {
            CharactersScreen(navController = navController)
        }
    }
}