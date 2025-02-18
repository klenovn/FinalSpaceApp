package com.klenovn.finalspaceapp.domain.model

data class Location(
    val id: Int,
    val imgUrl: String,
    val inhabitants: List<String>,
    val name: String,
    val notableResidents: List<String>,
    val type: String
)
