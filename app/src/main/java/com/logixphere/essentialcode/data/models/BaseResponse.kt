package com.logixphere.essentialcode.data.models

sealed class BaseResponse<out T> {
    data class Success<out T>(val body: T? = null) : BaseResponse<T>()
    data class Failure(val responseCode: Int?, val message: String) : BaseResponse<Nothing>()
}

