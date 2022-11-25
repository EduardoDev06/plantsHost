package com.bootcampnttdata6.plantshost.features.auth.util

sealed class Resource <out R> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val message: String):Resource<Nothing>()
    object loading : Resource<Nothing>()
    object Finished : Resource<Nothing>()
}
