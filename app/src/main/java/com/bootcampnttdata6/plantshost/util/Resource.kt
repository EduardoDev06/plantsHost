package com.bootcampnttdata6.plantshost.util

sealed class Resource<out R>{
    object Loading:Resource<Nothing>()
    object Finish:Resource<Nothing>()
    data class Success<out T>(val data:T):Resource<T>()
    data class Error(val message:String):Resource<Nothing>()
}
