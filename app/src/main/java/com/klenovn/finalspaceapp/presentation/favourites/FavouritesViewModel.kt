package com.klenovn.finalspaceapp.presentation.favourites

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesState())
    val state = _state.asStateFlow()

    init {
        getFavourites()
    }

    private fun getFavourites() {
        var favourites: List<Character>

        viewModelScope.launch {
            characterRepository
                .getAllFavouriteCharacters()
                .collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        favourites = result.data
                        favourites.map { it.isFavourite = true }
                        _state.value = FavouritesState(characters = favourites)
                    }
                    is ResourceState.Loading -> {}
                    is ResourceState.Error -> {}
                }
            }
        }
    }

    fun toggleFavourite(character: Character) {
        viewModelScope.launch {
            val resultFlow: Flow<ResourceState<Number>> = when (character.isFavourite) {
                true -> characterRepository.deleteFavouriteCharacter(character.toCharacterEntity())
                false -> return@launch
            }

            resultFlow.collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        updateCharacterFavouriteState(characterId = character.id, isFavourite = !character.isFavourite)
                        _state.value.characters = _state.value.characters.filter { it.isFavourite }
                    }
                    is ResourceState.Error -> {  }
                    is ResourceState.Loading -> {  }
                }
            }
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

    fun onLaunch() {
        viewModelScope.launch {
            getFavourites()
        }
    }

    fun onRetry() {
        getFavourites()
    }
}