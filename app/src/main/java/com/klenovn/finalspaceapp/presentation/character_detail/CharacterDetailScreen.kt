package com.klenovn.finalspaceapp.presentation.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.common.components.CharacterImage
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableInfoSection
import com.klenovn.finalspaceapp.presentation.common.components.InfoRow
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
import com.klenovn.finalspaceapp.presentation.common.components.ScreenLoadingIndicator

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Content(
        state = state,
        onBackClick = navController::popBackStack,
        onToggleFavourite = viewModel::onToggleFavourite,
        onRetry = viewModel::onRetry
    )
}

@Composable
private fun Content(
    state: CharacterDetailState,
    onBackClick: () -> Unit,
    onToggleFavourite: (Character) -> Unit,
    onRetry: () -> Unit
) {
    val character = state.character

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button"
            )
        }
        character?.let {
            IconButton(onClick = { onToggleFavourite(it) }) {
                when (character.isFavourite) {
                    true -> Icon(imageVector = Icons.Filled.Favorite, tint = Color.Red, contentDescription = "Favourite button")
                    else -> Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favourite button")
                }
            }
        }
    }

    when {
        state.isLoading -> {
            ScreenLoadingIndicator()
        }
        state.error != null -> {
            RetryOnError { onRetry() }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                character?.let { character ->
                    CharacterImage(imgUrl = character.imgUrl, imgFile = character.imgFile)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(label = "Species", value = character.species ?: "Unknown")
                    InfoRow(label = "Gender", value = character.gender)
                    InfoRow(label = "Hair", value = character.hair)
                    InfoRow(label = "Origin", value = character.origin)

                    ExpandableInfoSection(
                        label = "Abilities",
                        content = character.abilities
                    )
                    ExpandableInfoSection(
                        label = "Alias",
                        content = character.alias
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}