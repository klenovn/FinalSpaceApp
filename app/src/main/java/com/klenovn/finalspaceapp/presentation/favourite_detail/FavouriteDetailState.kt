package com.klenovn.finalspaceapp.presentation.favourite_detail

import com.klenovn.finalspaceapp.domain.model.Character

data class FavouriteDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
