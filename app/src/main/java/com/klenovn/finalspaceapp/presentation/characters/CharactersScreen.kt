package com.klenovn.finalspaceapp.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.common.components.CharacterCard
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
import com.klenovn.finalspaceapp.presentation.common.components.ScreenLoadingIndicator
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetailRoute

@Composable
fun CharactersScreen(
    navController: NavController,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LifecycleResumeEffect(Unit) {
        viewModel.onResume()
        onPauseOrDispose {  }
    }

    Content(
        state = state,
        onRetry = viewModel::onRetry,
        onToggleFavourite = viewModel::onToggleFavourite,
        onCharacterClick = { characterId -> navController.navigate(CharacterDetailRoute(characterId)) }
    )
}

@Composable
private fun Content(
    state: CharactersState,
    onRetry: () -> Unit,
    onToggleFavourite: (Character) -> Unit,
    onCharacterClick: (Int) -> Unit
) {
    when {
        state.isLoading -> {
            ScreenLoadingIndicator()
        }
        state.error != null -> {
            RetryOnError { onRetry() }
        }
        else -> {
            Box {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(state.characters) {
                        CharacterCard(
                            character = it,
                            isFromNetwork = true,
                            isFavourite = it.isFavourite,
                            onCardClick = { onCharacterClick(it.id) }
                        ) {
                            onToggleFavourite(it)
                        }
                    }
                }
            }
        }
    }
}