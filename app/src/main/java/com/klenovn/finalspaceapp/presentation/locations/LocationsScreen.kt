package com.klenovn.finalspaceapp.presentation.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.common.components.LocationImage
import com.klenovn.finalspaceapp.presentation.common.components.RetryOnError
import com.klenovn.finalspaceapp.presentation.common.components.ScreenLoadingIndicator
import com.klenovn.finalspaceapp.presentation.common.components.ScrollList
import com.klenovn.finalspaceapp.presentation.navigation.LocationDetailRoute
import kotlin.math.absoluteValue

@Composable
fun LocationsScreen(
    navController: NavController,
    viewModel: LocationsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Content(
        state = state,
        onLocationClick = { locationId -> navController.navigate(LocationDetailRoute(locationId)) },
        onRetry = {}
    )
}

@Composable
private fun Content(
    state: LocationsState,
    onLocationClick: (id: Int) -> Unit,
    onRetry: () -> Unit
) {
    when {
        state.isLoading -> {
            ScreenLoadingIndicator()
        }

        state.error != null -> {
            RetryOnError { onRetry() }
        }

        else -> {
            ScrollList(items = state.locations) { page, pagerState ->
                val item = state.locations[page]

                Card(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                            scaleX = lerp(
                                start = 0.8f,
                                stop = 1.1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1.5f)
                            )

                            scaleY = lerp(
                                start = 0.8f,
                                stop = 1.1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1.5f)
                            )
                        }
                        .clickable { onLocationClick(item.id) }
                ) {
                    val imageLabelColor = Color.Black.copy(alpha = 0.7f)

                    Box {
                        LocationImage(imgUrl = item.imgUrl)

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .border(
                                    color = imageLabelColor,
                                    width = 1.dp,
                                    shape = RoundedCornerShape(50f)
                                )
                                .background(
                                    color = imageLabelColor,
                                    shape = RoundedCornerShape(50f)
                                )
                                .align(Alignment.TopEnd)
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}