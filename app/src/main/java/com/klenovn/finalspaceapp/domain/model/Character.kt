package com.klenovn.finalspaceapp.domain.model

import java.io.File

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String?,
    val gender: String,
    val hair: String,
    val alias: List<String>,
    val origin: String,
    val abilities: List<String>,
    val imgUrl: String,
    var isFavourite: Boolean,
    val imgFile: File?
)