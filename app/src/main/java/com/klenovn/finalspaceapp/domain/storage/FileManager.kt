package com.klenovn.finalspaceapp.domain.storage

import java.io.File

interface FileManager {
    suspend fun saveImage(imageUrl: String, fileName: String): String

    fun loadImage(fileName: String): File?

    fun deleteImage(fileName: String): Boolean
}