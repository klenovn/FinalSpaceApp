package com.klenovn.finalspaceapp.domain.repository

import android.content.Context
import com.klenovn.finalspaceapp.data.local.CharacterEntity
import com.klenovn.finalspaceapp.domain.model.Character
import com.klenovn.finalspaceapp.util.ResourceState
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacters(): Flow<ResourceState<List<Character>>>

    suspend fun getCharacter(id: Int): Flow<ResourceState<Character>>

    suspend fun getAllFavouriteCharacters(): Flow<ResourceState<List<Character>>>

    suspend fun getFavouriteCharacter(id: Int): Flow<ResourceState<Character>>

    suspend fun addFavouriteCharacter(character: Character): Flow<ResourceState<Long>>

    suspend fun deleteFavouriteCharacter(characterEntity: CharacterEntity): Flow<ResourceState<Int>>

    suspend fun deleteFavouriteCharacters(): Flow<ResourceState<Int>>
}
