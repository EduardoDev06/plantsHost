package com.bootcampnttdata6.plantshost.util

sealed class Result<out T> {
    class  Loading<out T>: Result<T>()
    object Finish:Result<Nothing>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
}