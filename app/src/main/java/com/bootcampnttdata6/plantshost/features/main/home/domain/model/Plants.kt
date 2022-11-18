package com.bootcampnttdata6.plantshost.features.main.home.domain.model

data class Plants(
    val id:Int,
    val description: String,
    val image: String,
    val nameplant: String,
    val price: String,
    val status: String,
    var isFavorite: Boolean
)