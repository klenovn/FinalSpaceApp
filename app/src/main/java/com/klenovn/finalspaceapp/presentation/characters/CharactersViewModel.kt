package com.klenovn.finalspaceapp.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klenovn.finalspaceapp.domain.use_case.character.GetAllCharactersUseCase
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {
    private val TAG = "CharsVM"
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase()
                .onEach { result ->
                    when (result) {
                        is ResourceState.Success -> {
                            _state.value = CharactersState(characters = result.data)
                            Log.d(TAG, "Success")
                        }

                        is ResourceState.Loading -> {
                            _state.value = CharactersState(isLoading = true)
                            Log.d(TAG, "Loading")
                        }

                        is ResourceState.Error -> {
                            _state.value = CharactersState(error = result.message)
                            Log.d(TAG, "Error")
                        }
                    }
                }.launchIn(this)
        }
    }
}