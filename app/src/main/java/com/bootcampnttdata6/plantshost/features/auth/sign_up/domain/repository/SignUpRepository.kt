package com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository

import com.google.firebase.auth.AuthResult

interface SignUpRepository{
    suspend fun createAuthUser(email:String,password:String)
}