package com.klenovn.finalspaceapp.common

sealed class ResourceState<out T> {
    data object Idle : ResourceState<Nothing>()
    data object Loading : ResourceState<Nothing>()
    class Error(val message: String) : ResourceState<Nothing>()
    class Success<out T>(val data: T) : ResourceState<T>()
}