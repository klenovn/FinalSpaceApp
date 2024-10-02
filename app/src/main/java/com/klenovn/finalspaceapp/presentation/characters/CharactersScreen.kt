package com.klenovn.finalspaceapp.presentation.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.common.components.CharacterCard
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetail

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

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.error != null) {
        RetryOnError { viewModel.onRetry() }
    } else {
        Box {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.characters) {
                    CharacterCard(
                        character = it,
                        isFromNetwork = true,
                        isFavourite = it.isFavourite,
                        modifier = Modifier.clickable {
                            navController.navigate(CharacterDetail(it.id))
                        }
                    ) {
                        viewModel.toggleFavourite(it)
                    }
                }
            }
        }
    }
}
