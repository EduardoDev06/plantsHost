package com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository

import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel
import kotlinx.coroutines.flow.Flow

interface PlantFavoriteRepository {
    fun getPlants(): Flow<List<PlantModel>>
}