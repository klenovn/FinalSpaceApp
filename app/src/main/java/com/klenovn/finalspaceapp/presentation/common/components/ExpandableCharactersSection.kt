package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.presentation.ui.theme.ExtendedTheme

@Composable
fun ExpandableCharactersSection(
    label: String,
    content: List<Character>,
    onCardClick: (Int) -> Unit,
    onToggleFavourite: (Character) -> Unit
) = Column {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = ExtendedTheme.colors.sectionContentBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = ExtendedTheme.colors.sectionHeaderBackground)
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Light)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        if (isExpanded) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(content) {
                    CharacterCard(
                        character = it,
                        modifier = Modifier
                            .width((screenWidth / 2).dp),
                        isFavourite = it.isFavourite,
                        onCardClick = { onCardClick(it.id) },
                        onFavouriteToggle = { onToggleFavourite(it) }
                    )
                }
            }
        }
    }

    if (isExpanded) {
        Spacer(modifier = Modifier.size(48.dp))
    }
}
