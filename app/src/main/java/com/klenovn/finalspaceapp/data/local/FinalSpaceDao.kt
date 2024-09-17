package com.klenovn.finalspaceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FinalSpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity): Long

    @Query("DELETE FROM characterentity WHERE id = :id")
    suspend fun deleteCharacter(id: Int): Int

    @Query("DELETE FROM characterentity")
    suspend fun clearCharacters(): Int

    @Query("SELECT * FROM characterentity")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characterentity WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity

    @Query("""
        SELECT *
        FROM characterentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
    """)
    suspend fun searchCharacter(query: String): List<CharacterEntity>
}