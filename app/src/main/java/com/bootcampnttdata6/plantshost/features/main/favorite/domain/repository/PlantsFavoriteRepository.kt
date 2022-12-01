package com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository

import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import kotlinx.coroutines.flow.Flow

interface PlantFavoriteRepository {
    fun getPlantsFavorite(): Flow<List<Plants>>
}