package com.klenovn.finalspaceapp.presentation.characters

import com.klenovn.finalspaceapp.domain.model.Character

data class CharactersState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)