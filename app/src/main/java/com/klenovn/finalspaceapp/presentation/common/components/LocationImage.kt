package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun LocationImage(modifier: Modifier = Modifier, imgUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = imgUrl
    )
    Image(
        painter = painter,
        contentDescription = "Planet's image",
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f),
        contentScale = ContentScale.Crop
    )
}