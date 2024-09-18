package com.klenovn.finalspaceapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String?,
    val gender: String,
    val hair: String,
    val alias: List<String>,
    val origin: String,
    val abilities: List<String>,
    val imgUrl: String,
)
