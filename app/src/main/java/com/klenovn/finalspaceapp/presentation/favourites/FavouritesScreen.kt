package com.klenovn.finalspaceapp.presentation.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.common.components.CharacterCard
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetail
import com.klenovn.finalspaceapp.presentation.navigation.FavouriteDetail

@Composable
fun FavouritesScreen(
    navController: NavController,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onLaunch()
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(state.characters) {
           CharacterCard(
               character = it,
               isFromNetwork = false,
               isFavourite = it.isFavourite,
               modifier = Modifier.clickable {
                   navController.navigate(FavouriteDetail(it.id))
               }
           ) {
               viewModel.toggleFavourite(it)
           }
        }
    }
}