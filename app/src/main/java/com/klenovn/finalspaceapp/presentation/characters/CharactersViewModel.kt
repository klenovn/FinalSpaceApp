package com.klenovn.finalspaceapp.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klenovn.finalspaceapp.data.mapper.toCharacterEntity
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        var characters: List<Character>
        viewModelScope.launch {
            characterRepository
                .getAllCharacters()
                .collectLatest { result ->
                    when (result) {
                        is ResourceState.Success -> {
                            characters = result.data
                            characters = mapFavouriteCharacters(characters)
                            _state.value = CharactersState(characters = characters)
                        }

                        is ResourceState.Loading -> {
                            _state.value = CharactersState(isLoading = true)
                        }

                        is ResourceState.Error -> {
                            _state.value = CharactersState(error = result.message)
                        }
                    }
                }
        }
    }

    fun toggleFavourite(character: Character) {
        viewModelScope.launch {
            val resultFlow: Flow<ResourceState<Number>> = when (character.isFavourite) {
                true -> characterRepository.deleteFavouriteCharacter(character.toCharacterEntity())
                false -> characterRepository.addFavouriteCharacter(character)
            }

            resultFlow.collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        updateCharacterFavouriteState(characterId = character.id, isFavourite = !character.isFavourite)
                    }
                    is ResourceState.Error -> {  }
                    is ResourceState.Loading -> {  }
                }
            }
        }
    }

    private suspend fun mapFavouriteCharacters(characters: List<Character>): List<Character> {
        return characters.map { character ->
            var isFavourite = false
            characterRepository.getFavouriteCharacter(character.id).collect { result ->
                when (result) {
                    is ResourceState.Success -> { isFavourite = true }

                    is ResourceState.Loading -> {}

                    is ResourceState.Error -> { isFavourite = false }
                }
            }
            character.copy(isFavourite = isFavourite)
        }
    }

    private fun updateCharacterFavouriteState(characterId: Int, isFavourite: Boolean) {
        _state.value = _state.value.copy(
            characters = _state.value.characters.map {
                if (it.id == characterId) it.copy(isFavourite = isFavourite)
                else it
            }
        )
    }
}