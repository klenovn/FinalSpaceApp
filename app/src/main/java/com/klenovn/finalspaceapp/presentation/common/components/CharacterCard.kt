package com.klenovn.finalspaceapp.presentation.common.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.klenovn.finalspaceapp.domain.model.Character
import java.io.File

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    isFavourite: Boolean = false,
    isFromNetwork: Boolean = true,
    onFavouriteToggle: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp)
            .shadow(10.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            if (isFromNetwork) AsyncLoadingImage(imgUrl = character.imgUrl)
            else LocalImage(file = character.imgFile)

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
            ) {
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
            IconButton(
                onClick = { onFavouriteToggle() }
            ) {
                when (isFavourite) {
                    true -> Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Delete from favourite",
                        tint = Color.Red
                    )

                    else -> Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Add to favourite"
                    )
                }

            }
        }
    }
}

@Composable
fun AsyncLoadingImage(imgUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(Size.ORIGINAL)
            .build()
    )

    val imageState = painter.state
    when (imageState) {

        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                contentDescription = "Character's image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .aspectRatio(1f),
            )
        }

        else -> {
            Log.d("ImageState", "Unknown state")
        }
    }
}

@Composable
fun LocalImage(file: File?) {
    val painter = rememberAsyncImagePainter(model = file)

    Image(
        painter = painter,
        contentDescription = "Character's image",
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .aspectRatio(1f),
    )
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun Preview() {
    CharacterCard(
        character = Character(
            1,
            "Coolguy",
            "Coolguy",
            "Coolguy",
            "Coolguy",
            "Coolguy",
            emptyList<String>(),
            "Coolguy",
            emptyList<String>(),
            "https://finalspaceapi.com/api/character/avatar/mooncake.jpg",
            isFavourite = true,
            imgFile = null
        ),
        isFromNetwork = true
    ) {

    }
}