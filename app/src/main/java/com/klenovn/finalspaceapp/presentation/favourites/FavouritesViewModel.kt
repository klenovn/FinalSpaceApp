package com.klenovn.finalspaceapp.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
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
}