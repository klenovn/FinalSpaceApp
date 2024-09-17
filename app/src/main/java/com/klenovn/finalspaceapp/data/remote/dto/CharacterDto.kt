package com.klenovn.finalspaceapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.klenovn.finalspaceapp.domain.model.Character

data class CharacterDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair")
    val hair: String,
    @SerializedName("alias")
    val alias: List<String>,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("abilities")
    val abilities: List<String>,
    @SerializedName("img_url")
    val imgUrl: String,
)