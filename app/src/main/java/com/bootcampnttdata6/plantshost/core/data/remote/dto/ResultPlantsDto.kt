package com.bootcampnttdata6.plantshost.core.data.remote.dto

import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants

data class ResultPlantsDto(
    val id: Int,
    val description: String,
    val image: String,
    val nameplant: String,
    val price: String,
    val status: String,
) {
    fun toPlantsDomain() = Plants(
        id = id,
        nameplant = nameplant,
        description = description,
        image = image,
        price = price,
        status = status,
        isFavorite = false
    )

    fun toPlantsEntity() = PlantsEntity(
        id = id,
        description = description,
        nameplant = nameplant,
        image = image,
        price = price,
        status = status,
        isFavorite = false
    )
}
