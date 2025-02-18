package com.klenovn.finalspaceapp.domain.repository

import com.klenovn.finalspaceapp.domain.model.Location
import com.klenovn.finalspaceapp.util.ResourceState
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getAllLocations(): Flow<ResourceState<List<Location>>>

    suspend fun getLocationById(id: Int): Flow<ResourceState<Location>>
}