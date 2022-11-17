package com.bootcampnttdata6.plantshost.features.main.favorite.domain.usecase

import androidx.room.ColumnInfo
import com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlants @Inject constructor(
    private val repository: PlantFavoriteRepository
) {
    operator fun invoke(): Flow<List<PlantModel>> {
        return repository.getPlants().map { it.filter { it.isFavorite } }
    }
}