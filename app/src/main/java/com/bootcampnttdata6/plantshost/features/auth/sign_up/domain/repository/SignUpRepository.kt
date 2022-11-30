package com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository

interface SignUpRepository{
    suspend fun createAuthUser(email:String,password:String)
    suspend fun insertUser(email:String,name:String,address: String,age:Int)
}