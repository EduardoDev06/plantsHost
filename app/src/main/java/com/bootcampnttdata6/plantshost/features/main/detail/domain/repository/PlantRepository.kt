package com.bootcampnttdata6.plantshost.features.main.detail.domain.repository

import com.bootcampnttdata6.plantshost.features.main.detail.domain.model.Plant

interface PlantRepository {
    suspend fun getPlantById(id: Int): Plant?
}