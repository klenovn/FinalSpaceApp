package com.klenovn.finalspaceapp.presentation.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.klenovn.finalspaceapp.R

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    data class Characters(val context: Context) : BottomNavItem(
        title = context.getString(R.string.characters_nav_item),
        selectedIcon = Icons.Default.Face,
        unselectedIcon = Icons.Default.Face,
        route = CharactersRoute
    )
    data class Favourites(val context: Context) : BottomNavItem(
        title = context.getString(R.string.favourites_nav_item),
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Default.FavoriteBorder,
        route = FavouritesRoute
    )
    data class Locations(val context: Context) : BottomNavItem(
        title = context.getString(R.string.locations_nav_item),
        selectedIcon = Icons.Default.Place,
        unselectedIcon = Icons.Default.Place,
        route = LocationsRoute
    )
}
