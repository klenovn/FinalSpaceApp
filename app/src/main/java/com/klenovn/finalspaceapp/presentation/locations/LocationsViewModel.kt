package com.klenovn.finalspaceapp.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klenovn.finalspaceapp.domain.repository.LocationRepository
import com.klenovn.finalspaceapp.presentation.characters.CharactersState
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LocationsState())
    val state = _state.asStateFlow()

    init {
        getLocations()
    }

    private fun getLocations() {
        viewModelScope.launch {
            repository.getAllLocations().collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        val locations = result.data
                        _state.value = LocationsState(locations = locations)
                    }

                    is ResourceState.Loading -> {
                        _state.value = LocationsState(isLoading = true)
                    }

                    is ResourceState.Error -> {
                        _state.value = LocationsState(error = result.message)
                    }
                }
            }
        }
    }

}