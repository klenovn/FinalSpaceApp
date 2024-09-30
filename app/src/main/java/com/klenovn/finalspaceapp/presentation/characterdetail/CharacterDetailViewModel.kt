package com.klenovn.finalspaceapp.presentation.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.presentation.navigation.CharacterDetail
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
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
                        _state.value = CharacterDetailState(character = result.data)
                    }
                    is ResourceState.Loading -> {}
                    is ResourceState.Error -> {}
                }
            }
        }
    }
}