package com.klenovn.finalspaceapp.presentation.characterdetail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klenovn.finalspaceapp.presentation.characterdetail.components.CharacterImage
import com.klenovn.finalspaceapp.presentation.characterdetail.components.ExpandableInfoSection
import com.klenovn.finalspaceapp.presentation.characterdetail.components.InfoRow

@Composable
fun CharacterDetailScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val character = state.value.character
    Row {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button"
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        character?.let { character ->
            CharacterImage(imgUrl = character.imgUrl, imgFile = character.imgFile)
            Log.d("CharacterDetailScreen", "imgUrl: ${character.imgUrl}, imgFile: ${character.imgFile?.absolutePath}")
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
