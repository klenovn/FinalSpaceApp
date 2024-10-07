package com.klenovn.finalspaceapp.presentation.location_detail

import com.klenovn.finalspaceapp.domain.model.Location

data class LocationDetailState(
    val location: Location? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
