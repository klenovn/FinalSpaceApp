package com.klenovn.finalspaceapp.data.mapper

import com.klenovn.finalspaceapp.data.remote.dto.LocationDto
import com.klenovn.finalspaceapp.domain.model.Location

fun LocationDto.toLocation() : Location {
    return Location(
        id = id,
        imgUrl = imgUrl,
        inhabitants = inhabitants,
        name = name,
        notableResidents = notableResidents,
        type = type
    )
}