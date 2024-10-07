package com.klenovn.finalspaceapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    data object Characters : BottomNavItem(
        title = "Characters",
        selectedIcon = Icons.Default.Face,
        unselectedIcon = Icons.Default.Face,
        route = CharactersRoute
    )
    data object Favourites : BottomNavItem(
        title = "Favourites",
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Default.FavoriteBorder,
        route = FavouritesRoute
    )
    data object Locations : BottomNavItem(
        title = "Locations",
        selectedIcon = Icons.Default.Place,
        unselectedIcon = Icons.Default.Place,
        route = LocationsRoute
    )
}
