package com.klenovn.finalspaceapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharactersRoute

@Serializable
object FavouritesRoute

@Serializable
object LocationsRoute

@Serializable
data class CharacterDetailRoute(val id: Int)

@Serializable
data class FavouriteDetailRoute(val id: Int)

@Serializable
data class LocationDetailRoute(val id: Int)