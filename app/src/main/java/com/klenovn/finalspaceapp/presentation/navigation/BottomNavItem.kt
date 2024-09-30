package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    data object Characters : BottomNavItem("Characters", Icons.Default.Face, Icons.Default.Face, CharactersRoute)
    data object Favourites : BottomNavItem("Favourites", Icons.Default.Favorite, Icons.Default.FavoriteBorder, FavouritesRoute)
}
