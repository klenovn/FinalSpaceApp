package com.klenovn.finalspaceapp.presentation.location_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.klenovn.finalspaceapp.data.mapper.toCharacterEntity
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.domain.repository.LocationRepository
import com.klenovn.finalspaceapp.presentation.navigation.LocationDetailRoute
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val characterRepository: CharacterRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(LocationDetailState())
    val state = _state.asStateFlow()

    init {
        val locationDetailRoute = handle.toRoute<LocationDetailRoute>()
        val locationId = locationDetailRoute.id
        getLocation(locationId)
    }

    private fun getLocation(id: Int) {
        viewModelScope.launch {
            locationRepository.getLocationById(id).collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        _state.value = LocationDetailState(location = result.data)
                        getLocationCharacters()
                    }

                    is ResourceState.Loading -> {
                        _state.value = LocationDetailState(isLoading = true)
                    }

                    is ResourceState.Error -> {
                        _state.value = LocationDetailState(error = result.message)
                    }
                }
            }
        }
    }

    private suspend fun getLocationCharacters() {
        val residentsList = mutableListOf<Character>()
        _state.value.location?.notableResidents?.map { characterLink ->
            val characterId = characterLink.drop(characterLink.lastIndexOf("/") + 1).toInt()
            characterRepository.getCharacter(characterId).collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        residentsList.add(result.data)
                    }

                    else -> Unit
                }
            }
        }

        _state.value = _state.value.copy(notableResidents = mapFavouriteCharacters(residentsList))
    }

    fun onRetry() {
        val locationDetailRoute = handle.toRoute<LocationDetailRoute>()
        val locationId = locationDetailRoute.id
        getLocation(locationId)
    }

    fun onResume() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                notableResidents = mapFavouriteCharacters(
                    _state.value.notableResidents ?: emptyList()
                )
            )
        }
    }

    fun onToggleFavourite(character: Character) {
        viewModelScope.launch {
            val resultFlow: Flow<ResourceState<Number>> = when (character.isFavourite) {
                true -> characterRepository.deleteFavouriteCharacter(character.toCharacterEntity())
                false -> characterRepository.addFavouriteCharacter(character)
            }

            resultFlow.collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        updateCharacterFavouriteState(
                            characterId = character.id,
                            isFavourite = !character.isFavourite
                        )
                    }

                    is ResourceState.Error -> {}
                    is ResourceState.Loading -> {}
                }
            }
        }
    }

    private suspend fun mapFavouriteCharacters(characters: List<Character>): List<Character> {
        return characters.map { character ->
            var isFavourite = false
            characterRepository.getFavouriteCharacter(character.id).collect { result ->
                when (result) {
                    is ResourceState.Success -> {
                        isFavourite = true
                    }

                    is ResourceState.Loading -> {}

                    is ResourceState.Error -> {
                        isFavourite = false
                    }
                }
            }
            character.copy(isFavourite = isFavourite)
        }
    }

    private fun updateCharacterFavouriteState(characterId: Int, isFavourite: Boolean) {
        _state.value = _state.value.copy(
            notableResidents = _state.value.notableResidents?.map {
                if (it.id == characterId) it.copy(isFavourite = isFavourite)
                else it
            }
        )
    }
}