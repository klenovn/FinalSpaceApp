package com.klenovn.finalspaceapp.presentation.characters.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.klenovn.finalspaceapp.domain.model.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp)
            .shadow(10.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
        ) {
            AsyncImage(
                model = character.imgUrl,
                contentDescription = "Character's image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .aspectRatio(1f)
            )
            Text(
                text = character.name,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = character.origin,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun Preview() {
    CharacterCard(character = Character(
        1,
        "Coolguy",
        "Coolguy",
        "Coolguy",
        "Coolguy",
        "Coolguy",
        emptyList<String>(),
        "Coolguy",
        emptyList<String>(),
        "https://finalspaceapi.com/api/character/avatar/mooncake.jpg"
    ))
}