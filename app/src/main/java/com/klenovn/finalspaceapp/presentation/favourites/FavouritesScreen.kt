package com.klenovn.finalspaceapp.presentation.favourites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.characters.components.CharacterCard

@Composable
fun FavouritesScreen(
    navController: NavController,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(state.characters) {
           CharacterCard(character = it, isFavourite = it.isFavourite) {
               
           }
        }
    }
}