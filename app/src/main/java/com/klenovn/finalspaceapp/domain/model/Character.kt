package com.klenovn.finalspaceapp.domain.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val alias: List<String>,
    val origin: String,
    val abilities: List<String>,
    val imgUrl: String,
)