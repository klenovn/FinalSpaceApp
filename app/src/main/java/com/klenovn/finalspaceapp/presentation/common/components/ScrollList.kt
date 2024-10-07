package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ScrollList(
    modifier: Modifier = Modifier,
    items: List<Any>,
    content: @Composable (page: Int, pagerState: PagerState) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val pagerState = rememberPagerState(pageCount = { items.size })
    VerticalPager(
        state = pagerState,
        contentPadding = PaddingValues(
            vertical = (screenHeight / 3.5).dp,
        ),
        modifier = modifier
    ) {page ->
        content(page, pagerState)
    }
}