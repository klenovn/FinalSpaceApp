package com.klenovn.finalspaceapp.util

sealed class ResourceState<out T> {
    data object Loading : ResourceState<Nothing>()
    class Error(val message: String?) : ResourceState<Nothing>()
    class Success<out T>(val data: T) : ResourceState<T>()
}