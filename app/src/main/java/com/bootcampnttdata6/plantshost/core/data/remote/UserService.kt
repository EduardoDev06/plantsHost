package com.bootcampnttdata6.plantshost.core.data.remote

import com.bootcampnttdata6.plantshost.core.data.remote.dto.UserDto
import com.bootcampnttdata6.plantshost.di.FirebaseModule
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(private val firebase : FirebaseModule){

    companion object{
        const val USER_COLLECTION = "users"
    }

    suspend fun createUser( userDto: UserDto) = kotlin.runCatching {
        val userDto = hashMapOf(
            "userEmail" to userDto.userEmail,
            "userFullName" to userDto.userFullName,
            "userPassword" to userDto.userPassword,
            "userAddress" to userDto.userAddress,
            "userAge" to userDto.userAge
        )
        firebase.provideFirestore().collection(USER_COLLECTION).add(userDto).await()
    }.isSuccess
}