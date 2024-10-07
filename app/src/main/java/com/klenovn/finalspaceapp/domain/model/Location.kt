package com.klenovn.finalspaceapp.domain.model

import com.google.gson.annotations.SerializedName

data class Location(
    val id: Int,
    val imgUrl: String,
    val inhabitants: List<String>,
    val name: String,
    val notableResidents: List<String>,
    val type: String
)
