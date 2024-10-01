package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavItem>,
    selectedItemIndex: MutableIntState
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex.intValue == index,
                onClick = {
                    if (selectedItemIndex.intValue != index) {
                        selectedItemIndex.intValue = index
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true

                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex.intValue) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                        Text(text = item.title)
                    }
                }
            )
        }
    }
}