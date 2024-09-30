package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klenovn.finalspaceapp.presentation.characterdetail.CharacterDetailScreen
import com.klenovn.finalspaceapp.presentation.characters.CharactersScreen
import com.klenovn.finalspaceapp.presentation.favourites.FavouritesScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(modifier = modifier, navController = navController, startDestination = CharactersRoute) {

        composable<CharactersRoute> {
            CharactersScreen(navController = navController)
        }
        
        composable<FavouritesRoute> { 
            FavouritesScreen(navController = navController)
        }

        composable<CharacterDetail> { entry ->
            CharacterDetailScreen(navController)
        }
    }
}