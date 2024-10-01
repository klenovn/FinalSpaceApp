package com.klenovn.finalspaceapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharactersRoute

@Serializable
object FavouritesRoute

@Serializable
data class CharacterDetail(val id: Int)

@Serializable
data class FavouriteDetail(val id: Int)