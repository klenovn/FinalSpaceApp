package com.klenovn.finalspaceapp.presentation.location_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.klenovn.finalspaceapp.domain.repository.LocationRepository
import com.klenovn.finalspaceapp.presentation.favourite_detail.FavouriteDetailState
import com.klenovn.finalspaceapp.presentation.navigation.LocationDetailRoute
import com.klenovn.finalspaceapp.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository,
    handle: SavedStateHandle
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
            repository.getLocationById(id).collectLatest { result ->
                when (result) {
                    is ResourceState.Success -> {
                        _state.value = LocationDetailState(location = result.data)
                    }
                    is ResourceState.Loading -> { _state.value = LocationDetailState(isLoading = true) }
                    is ResourceState.Error -> { _state.value = LocationDetailState(error = result.message) }
                }
            }
        }
    }
}