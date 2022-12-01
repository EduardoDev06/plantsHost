package com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository

interface AuthRepository {
    suspend fun login(email: String,password:String):Boolean

}