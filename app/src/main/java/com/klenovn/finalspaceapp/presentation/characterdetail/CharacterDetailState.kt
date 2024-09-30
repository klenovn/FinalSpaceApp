package com.klenovn.finalspaceapp.presentation.characterdetail

import com.klenovn.finalspaceapp.domain.model.Character

data class CharacterDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)