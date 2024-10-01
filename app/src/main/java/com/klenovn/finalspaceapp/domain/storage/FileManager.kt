package com.klenovn.finalspaceapp.domain.storage

import java.io.File

interface FileManager {
    suspend fun saveNetworkImage(imageUrl: String, fileName: String): String

    suspend fun saveLocalImage(byteArray: ByteArray, fileName: String): String

    fun loadImage(fileName: String): File?

    fun deleteImage(fileName: String): Boolean
}