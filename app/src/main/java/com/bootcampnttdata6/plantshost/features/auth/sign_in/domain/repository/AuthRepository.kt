package com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun login(email: String,password:String):Boolean

}