package com.klenovn.finalspaceapp.presentation.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.common.components.CharacterCard
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
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

    Content(
        state = state,
        onRetry = viewModel::onRetry,
        onToggleFavourite = viewModel::onToggleFavourite,
        onFavouriteClick = { favouriteId -> navController.navigate(FavouriteDetail(favouriteId))}
    )
}

@Composable
private fun Content(
    state: FavouritesState,
    onRetry: () -> Unit,
    onToggleFavourite: (Character) -> Unit,
    onFavouriteClick: (Int) -> Unit
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        state.error != null -> {
            RetryOnError(onClick = onRetry)
        }
        else -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.characters) {
                    CharacterCard(
                        character = it,
                        isFromNetwork = false,
                        isFavourite = it.isFavourite,
                        modifier = Modifier.clickable {
                            onFavouriteClick(it.id)
                        }
                    ) {
                        onToggleFavourite(it)
                    }
                }
            }
        }
    }
}