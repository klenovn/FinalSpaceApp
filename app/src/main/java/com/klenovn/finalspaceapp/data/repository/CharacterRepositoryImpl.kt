package com.klenovn.finalspaceapp.data.repository

import com.klenovn.finalspaceapp.data.local.CharacterEntity
import com.klenovn.finalspaceapp.data.local.FinalSpaceDao
import com.klenovn.finalspaceapp.data.mapper.toCharacter
import com.klenovn.finalspaceapp.data.remote.FinalSpaceApi
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val api: FinalSpaceApi,
    private val dao: FinalSpaceDao
) : CharacterRepository {
    override suspend fun getAllCharacters(): Flow<ResourceState<List<Character>>> {
        return safeFlow {
            val data = api.getAllCharacters()
            data.map { it.toCharacter() }
        }
    }

    override suspend fun getCharacter(id: Int): Flow<ResourceState<Character>> {
        return safeFlow {
            val data = api.getCharacterById(id)
            data.toCharacter()
        }
    }

    override suspend fun getAllFavouriteCharacters(): Flow<ResourceState<List<Character>>> {
        return safeFlow {
            val data = dao.getAllCharacters()
            data.map { it.toCharacter() }
        }
    }

    override suspend fun getFavouriteCharacter(id: Int): Flow<ResourceState<Character>> {
        return safeFlow {
            val data = dao.getCharacter(id)
            data.toCharacter()
        }
    }

    override suspend fun addFavouriteCharacter(characterEntity: CharacterEntity): Flow<ResourceState<Long>> {
        return safeFlow {
            dao.insertCharacter(characterEntity)
        }
    }

    override suspend fun deleteFavouriteCharacter(id: Int): Flow<ResourceState<Int>> {
        return safeFlow {
            dao.deleteCharacter(id)
        }
    }

    override suspend fun deleteFavouriteCharacters(): Flow<ResourceState<Int>> {
        return safeFlow {
            dao.clearCharacters()
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
