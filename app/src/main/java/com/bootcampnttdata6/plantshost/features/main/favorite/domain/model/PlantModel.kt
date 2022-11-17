package com.bootcampnttdata6.plantshost.features.main.favorite.domain.model

data class PlantModel(
    val id: Int,
    val nameplant: String,
    val price: String,
    val status: Int,
    val image: String,
    val description: String,
    val isFavorite:Boolean
)
