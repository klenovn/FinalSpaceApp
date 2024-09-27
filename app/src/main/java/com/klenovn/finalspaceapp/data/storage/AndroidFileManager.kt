package com.klenovn.finalspaceapp.data.storage

import android.content.Context
import com.klenovn.finalspaceapp.domain.storage.FileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class AndroidFileManager @Inject constructor(
    private val context: Context
) : FileManager {
    override suspend fun saveImage(imageUrl: String, fileName: String): String {
        return withContext(Dispatchers.IO) {
            val file = File(context.filesDir, fileName)
            try {
                val url = URL(imageUrl)
                url.openStream().use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            fileName
        }
    }

    override fun loadImage(fileName: String): File? {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file else null
    }

    override fun deleteImage(fileName: String): Boolean {
        val file = File(context.filesDir, fileName)
        return file.delete()
    }
}