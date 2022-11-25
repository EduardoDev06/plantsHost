package com.bootcampnttdata6.plantshost.util

/*
sealed interface Resource<out T> {
    object Loading : Resource<Nothing>
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val data: T? = null, val error: Throwable? = null) : Resource<T>
}*/
sealed class Resource<out R>{
    object Loading:Resource<Nothing>()
    object Finish:Resource<Nothing>()
    data class Success<out T>(val data:T):Resource<T>()
    data class Error(val message:String):Resource<Nothing>()
}
