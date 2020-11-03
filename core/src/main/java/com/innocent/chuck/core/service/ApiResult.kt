package com.innocent.chuck.core.service

sealed class ApiResult <T> {

    data class Progress<T>(var loading: Boolean) : ApiResult<T>()
    data class Success<T>(var data: T) : ApiResult<T>()
    data class Failure<T>(val e: Throwable) : ApiResult<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): ApiResult<T> = Progress(isLoading)

        fun <T> success(data: T): ApiResult<T> = Success(data)

        fun <T> failure(e: Throwable): ApiResult<T> = Failure(e)
    }
}