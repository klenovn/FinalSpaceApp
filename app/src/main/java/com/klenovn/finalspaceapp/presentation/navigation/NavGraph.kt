package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klenovn.finalspaceapp.presentation.character_detail.CharacterDetailScreen
import com.klenovn.finalspaceapp.presentation.characters.CharactersScreen
import com.klenovn.finalspaceapp.presentation.favourite_detail.FavouriteDetailScreen
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

        composable<CharacterDetail> {
            CharacterDetailScreen(navController = navController)
        }

        composable<FavouriteDetail> {
            FavouriteDetailScreen(navController = navController)
        }
    }
}