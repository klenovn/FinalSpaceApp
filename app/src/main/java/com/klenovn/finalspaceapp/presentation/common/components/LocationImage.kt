package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.klenovn.finalspaceapp.R

@Composable
fun LocationImage(modifier: Modifier = Modifier, imgUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = imgUrl
    )
    Image(
        painter = painter,
        contentDescription = stringResource(R.string.planet_image_desc),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f),
        contentScale = ContentScale.Crop
    )
}