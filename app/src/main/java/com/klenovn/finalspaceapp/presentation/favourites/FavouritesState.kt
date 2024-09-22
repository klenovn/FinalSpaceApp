package com.klenovn.finalspaceapp.presentation.favourites

import com.klenovn.finalspaceapp.domain.model.Character
import kotlinx.coroutines.flow.MutableStateFlow

data class FavouritesState(
    var characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)