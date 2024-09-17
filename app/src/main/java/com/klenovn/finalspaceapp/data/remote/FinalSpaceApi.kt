package com.klenovn.finalspaceapp.data.remote

import com.klenovn.finalspaceapp.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FinalSpaceApi {
    @GET("/api/v0/character")
    suspend fun getAllCharacters(): List<CharacterDto>

    @GET("/api/v0/character/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Int): CharacterDto
}