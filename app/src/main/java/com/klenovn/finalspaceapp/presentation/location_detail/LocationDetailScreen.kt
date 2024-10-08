package com.klenovn.finalspaceapp.presentation.location_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableCharactersSection
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableInfoSection
import com.klenovn.finalspaceapp.presentation.common.components.InfoRow
import com.klenovn.finalspaceapp.presentation.common.components.LocationImage
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError

@Composable
fun LocationDetailScreen(
    navController: NavController,
    viewModel: LocationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Content(
        state = state,
        onBackClick = { navController.popBackStack() },
        onRetry = viewModel::onRetry
    )
}

@Composable
private fun Content(
    state: LocationDetailState,
    onBackClick: () -> Unit,
    onRetry: () -> Unit
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
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
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
                    LocationImage(imgUrl = location.imgUrl, modifier = Modifier.clip(shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)))
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
                            if (location.inhabitants.isNotEmpty()) ExpandableInfoSection(label = "Inhabitants", content = location.inhabitants)
                        }

                        if (location.notableResidents.isNotEmpty()) ExpandableCharactersSection(label = "Notable residents", content = state.notableResidents ?: emptyList())
                    }
                }
            }
        }
    }
}
