package com.klenovn.finalspaceapp.presentation.character_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.klenovn.finalspaceapp.data.mapper.toCharacterEntity
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetail
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    handle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterDetailState())
    val state = _state.asStateFlow()

    init {
        val characterDetail = handle.toRoute<CharacterDetail>()
        val characterId = characterDetail.id
        fetchCharacterInfo(characterId)
    }

    private fun fetchCharacterInfo(id: Int) {
        viewModelScope.launch {
            characterRepository.getCharacter(id).collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        _state.value = CharacterDetailState(character = result.data.copy(isFavourite = isFavourite(id)))
                    }
                    is ResourceState.Loading -> { _state.value = CharacterDetailState(isLoading = true) }
                    is ResourceState.Error -> {}
                }
            }
        }
    }

    private suspend fun isFavourite(id: Int): Boolean {
        var isFavourite = false
        characterRepository.getFavouriteCharacter(id).collectLatest { result ->
            when (result) {
                is ResourceState.Success -> isFavourite = true
                is ResourceState.Error -> isFavourite = false
                else -> {}
            }
        }

        return isFavourite
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
                        _state.value = _state.value.copy(character = _state.value.character?.copy(isFavourite = !character.isFavourite))
                    }
                    is ResourceState.Error -> {  }
                    is ResourceState.Loading -> {  }
                }
            }
        }
    }
}