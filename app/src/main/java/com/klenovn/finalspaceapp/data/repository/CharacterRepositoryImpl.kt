package com.klenovn.finalspaceapp.data.repository

import com.klenovn.finalspaceapp.data.local.CharacterEntity
import com.klenovn.finalspaceapp.data.local.FinalSpaceDao
import com.klenovn.finalspaceapp.data.mapper.toCharacter
import com.klenovn.finalspaceapp.data.mapper.toCharacterEntity
import com.klenovn.finalspaceapp.data.remote.FinalSpaceApi
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.domain.storage.FileManager
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
    private val dao: FinalSpaceDao,
    private val fileManager: FileManager
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
            data.map { it.toCharacter().copy(imgFile = fileManager.loadImage(fileName = it.imgFileName)) }
        }
    }

    override suspend fun getFavouriteCharacter(id: Int): Flow<ResourceState<Character>> {
        return safeFlow {
            val data = dao.getCharacter(id)
            data.toCharacter().copy(imgFile = fileManager.loadImage(fileName = data.imgFileName))
        }
    }

    override suspend fun addFavouriteCharacter(character: Character, byteArray: ByteArray?): Flow<ResourceState<Long>> {
        return safeFlow {
            var characterEntity = character.toCharacterEntity()
            val imgFilename = if (byteArray != null) {
                fileManager.saveLocalImage(byteArray, characterEntity.imgFileName)
            } else {
                fileManager.saveNetworkImage(character.imgUrl, characterEntity.imgFileName)
            }
            characterEntity = characterEntity.copy(imgFileName = imgFilename)
            dao.insertCharacter(characterEntity)
        }
    }

    override suspend fun deleteFavouriteCharacter(characterEntity: CharacterEntity): Flow<ResourceState<Int>> {
        return safeFlow {
            fileManager.deleteImage(characterEntity.imgFileName)
            dao.deleteCharacter(characterEntity.id)
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
