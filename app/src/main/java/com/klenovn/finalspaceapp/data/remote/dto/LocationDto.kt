package com.klenovn.finalspaceapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("inhabitants")
    val inhabitants: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("notable_residents")
    val notableResidents: List<String>,
    @SerializedName("type")
    val type: String
)