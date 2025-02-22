package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.klenovn.finalspaceapp.R
import java.io.File

@Composable
fun CharacterImage(imgUrl: String, imgFile: File?) {
    val painter = rememberAsyncImagePainter(
        model = imgFile ?: imgUrl
    )
    Image(
        painter = painter,
        contentDescription = stringResource(R.string.characters_image_desc),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
    )
}