package com.klenovn.finalspaceapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.klenovn.finalspaceapp.presentation.ui.theme.ExtendedTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandableInfoSection(label: String, content: List<String>, bottomMargin: Int = 16) = Column {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val cleanedContent = cleanAliases(content)

    if (content.isNotEmpty()) {
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
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = ExtendedTheme.colors.sectionContentBackground)
                ) {
                    cleanedContent.forEach {
                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(ExtendedTheme.colors.flowItemBackground)
                        )
                        {
                            Text(
                                text = it,
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.size(bottomMargin.dp))
    }
}

private fun cleanAliases(aliases: List<String>): List<String> {
    return aliases.map { alias ->
        alias.substringBefore("(").trim()
    }
}
