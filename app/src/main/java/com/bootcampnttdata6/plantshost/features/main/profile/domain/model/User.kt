package com.bootcampnttdata6.plantshost.features.main.profile.domain.model

data class User(
    val userFullName: String? = "",
    val userAge: Int? = 0,
    val userEmail: String = "",
    val userPassword: String = "",
    val userAddress: String? = "",
    val userImage: String? = "",
)