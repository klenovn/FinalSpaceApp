package com.klenovn.finalspaceapp.presentation.location_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableCharactersSection
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableInfoSection
import com.klenovn.finalspaceapp.presentation.common.components.InfoRow
import com.klenovn.finalspaceapp.presentation.common.components.LocationImage
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
import com.klenovn.finalspaceapp.presentation.common.components.ScreenLoadingIndicator
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetailRoute

@Composable
fun LocationDetailScreen(
    navController: NavController,
    viewModel: LocationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LifecycleResumeEffect(Unit) {
        viewModel.onResume()
        onPauseOrDispose {  }
    }

    Content(
        state = state,
        onBackClick = { navController.popBackStack() },
        onRetry = viewModel::onRetry,
        onCardClick = { id -> navController.navigate(CharacterDetailRoute(id)) },
        onToggleFavourite = viewModel::onToggleFavourite
    )
}

@Composable
private fun Content(
    state: LocationDetailState,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
    onCardClick: (Int) -> Unit,
    onToggleFavourite: (Character) -> Unit
) {
    val location = state.location

    IconButton(
        onClick = onBackClick,
        modifier = Modifier
            .offset(8.dp, 8.dp)
            .zIndex(100f)
            .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back button"
        )
    }

    when {
        state.isLoading -> {
            ScreenLoadingIndicator()
        }
        state.error != null -> {
            RetryOnError { onRetry() }
        }
        else -> {
            location?.let {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    LocationImage(
                        imgUrl = location.imgUrl,
                        modifier = Modifier.clip(
                            shape = RoundedCornerShape(
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        Column(modifier = Modifier
                            .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = location.name,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            InfoRow(label = "Species", value = location.type)
                            if (location.inhabitants.isNotEmpty()) ExpandableInfoSection(
                                label = "Inhabitants",
                                content = location.inhabitants
                            )
                        }

                        if (location.notableResidents.isNotEmpty()) ExpandableCharactersSection(
                            label = "Notable residents",
                            content = state.notableResidents ?: emptyList(),
                            onCardClick = { id -> onCardClick(id) },
                            onToggleFavourite = { character -> onToggleFavourite(character) }
                        )
                    }
                }
            }
        }
    }
}
