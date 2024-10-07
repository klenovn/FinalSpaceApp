package com.klenovn.finalspaceapp.presentation.location_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.common.components.ExpandableInfoSection
import com.klenovn.finalspaceapp.presentation.common.components.InfoRow
import com.klenovn.finalspaceapp.presentation.common.components.LocationImage

@Composable
fun LocationDetailScreen(
    navController: NavController,
    viewModel: LocationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Content(state = state)
}

@Composable
private fun Content(state: LocationDetailState) {
    val location = state.location
    location?.let {
        Column {
            LocationImage(imgUrl = location.imgUrl)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = location.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(label = "Species", value = location.type)

            ExpandableInfoSection(label = "Notable residents", content = location.notableResidents)
            ExpandableInfoSection(label = "Inhabitants", content = location.inhabitants)
        }
    }
}