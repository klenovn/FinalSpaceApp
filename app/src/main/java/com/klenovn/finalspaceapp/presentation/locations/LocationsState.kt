package com.klenovn.finalspaceapp.presentation.locations

import com.klenovn.finalspaceapp.domain.model.Location

data class LocationsState(
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
