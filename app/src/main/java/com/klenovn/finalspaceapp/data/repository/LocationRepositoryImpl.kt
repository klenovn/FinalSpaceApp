package com.klenovn.finalspaceapp.data.repository

import com.klenovn.finalspaceapp.data.mapper.toLocation
import com.klenovn.finalspaceapp.data.remote.FinalSpaceApi
import com.klenovn.finalspaceapp.domain.model.Location
import com.klenovn.finalspaceapp.domain.repository.LocationRepository
import com.klenovn.finalspaceapp.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: FinalSpaceApi
) : LocationRepository {

    override suspend fun getAllLocations(): Flow<ResourceState<List<Location>>> {
        return safeFlow {
            val locations = api.getAllLocations()
            locations.map { it.toLocation() }
        }
    }

    override suspend fun getLocationById(id: Int): Flow<ResourceState<Location>> {
        return safeFlow {
            val location = api.getLocationById(id)
            location.toLocation()
        }
    }
}

private inline fun <T> safeFlow(crossinline action: suspend () -> T): Flow<ResourceState<T>> = flow {
    emit(ResourceState.Loading)
    try {
        val result = action()
        emit(ResourceState.Success(result))
    } catch (e: IOException) {
        emit(ResourceState.Error(e.localizedMessage ?: "Network error"))
    } catch (e: HttpException) {
        emit(ResourceState.Error(e.localizedMessage ?: "HTTP error"))
    } catch (e: Exception) {
        emit(ResourceState.Error(e.localizedMessage ?: "Unknown error"))
    }
}