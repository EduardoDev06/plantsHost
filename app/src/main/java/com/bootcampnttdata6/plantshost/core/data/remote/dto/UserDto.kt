package com.bootcampnttdata6.plantshost.core.data.remote.dto

data class UserDto (
    val userEmail : String = "",
    val userFullName :String ="",
    val userPassword : String = "",
    val userAddress : String="",
    val userAge : Int = -1,
    //val showErrorDialog: Boolean = false
){
    fun isNotEmpty() =
        userEmail.isNotEmpty() && userFullName.isNotEmpty() && userPassword.isNotEmpty() && userAddress.isNotEmpty() &&  userAge!=null
}