package com.klenovn.finalspaceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FinalSpaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("DELETE FROM characterentity")
    suspend fun clearCharacters()

    @Query("""
        SELECT *
        FROM characterentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
    """)
    suspend fun searchCharacter(query: String): List<CharacterEntity>
}