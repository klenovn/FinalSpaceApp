package com.klenovn.finalspaceapp.presentation.character_detail

import com.klenovn.finalspaceapp.domain.model.Character

data class CharacterDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)