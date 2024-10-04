package com.klenovn.finalspaceapp.presentation.favourite_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.klenovn.finalspaceapp.data.mapper.toCharacterEntity
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.presentation.navigation.FavouriteDetail
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val hande: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(FavouriteDetailState())
    val state = _state.asStateFlow()
    private var byteArray: ByteArray? = null

    init {
        val favouriteDetail = hande.toRoute<FavouriteDetail>()
        val favouriteId = favouriteDetail.id
        fetchCharacterInfo(favouriteId)
    }

    private fun fetchCharacterInfo(id: Int) {
        viewModelScope.launch {
            characterRepository.getFavouriteCharacter(id).collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        _state.value = FavouriteDetailState(character = result.data.copy(isFavourite = isFavourite(id)))
                    }
                    is ResourceState.Loading -> { _state.value = FavouriteDetailState(isLoading = true) }
                    is ResourceState.Error -> { _state.value = FavouriteDetailState(error = result.message)
                    }
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

    fun onToggleFavourite(character: Character) {
        viewModelScope.launch {
            if (character.imgFile != null && character.imgFile.exists()) {
                byteArray = character.imgFile.readBytes()
            }
            val resultFlow: Flow<ResourceState<Number>> = when (character.isFavourite) {
                true -> characterRepository.deleteFavouriteCharacter(character.toCharacterEntity())
                else -> characterRepository.addFavouriteCharacter(character, byteArray)
            }

            resultFlow.collect { result ->
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

    fun onRetry() {
        val favouriteDetail = hande.toRoute<FavouriteDetail>()
        val favouriteId = favouriteDetail.id
        fetchCharacterInfo(favouriteId)
    }
}