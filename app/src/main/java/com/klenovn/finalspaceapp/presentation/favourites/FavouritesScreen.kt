package com.klenovn.finalspaceapp.presentation.favourites

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.common.components.CharacterCard

@Composable
fun FavouritesScreen(
    navController: NavController,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(state.characters) {
           CharacterCard(character = it, isFromNetwork = false, isFavourite = it.isFavourite) {
               viewModel.toggleFavourite(it)
           }
        }
    }
}