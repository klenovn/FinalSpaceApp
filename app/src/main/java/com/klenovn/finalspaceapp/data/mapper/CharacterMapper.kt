package com.klenovn.finalspaceapp.data.mapper

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.klenovn.finalspaceapp.data.local.CharacterEntity
import com.klenovn.finalspaceapp.data.remote.dto.CharacterDto
import com.klenovn.finalspaceapp.domain.model.Character

fun CharacterDto.toCharacter() : Character {
    return Character(
        id = id,
        name = name.trim(),
        status = status,
        species = species,
        gender = gender,
        hair = hair,
        alias = alias,
        origin = origin,
        abilities = abilities,
        imgUrl = imgUrl,
        isFavourite = false,
        imgFile = null
    )
}

fun CharacterEntity.toCharacter() : Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        hair = hair,
        alias = alias,
        origin = origin,
        abilities = abilities,
        imgUrl = imgFileName,
        isFavourite = true,
        imgFile = null
    )
}

fun Character.toCharacterEntity() : CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        hair = hair,
        alias = alias,
        origin = origin,
        abilities = abilities,
        imgFileName = "${name.replace(" ", "_").toLowerCase(Locale.current)}.jpg"
    )
}