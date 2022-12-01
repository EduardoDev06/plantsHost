package com.bootcampnttdata6.plantshost.features.main.detail.domain.repository

import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants

interface PlantRepository {
    suspend fun getPlantById(id: Int): Plants?
    suspend fun changeFav(id: Int, favorite: Boolean)
}