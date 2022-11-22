package com.bootcampnttdata6.plantshost.features.main.favorite.domain.usecase


import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlantsFavoriteUseCase @Inject constructor(
    private val repository: PlantFavoriteRepository
) {
    operator fun invoke(): Flow<List<Plants>> {
        return repository.getPlantsFavorite().map { it.filter {plants-> !plants.isFavorite } }
    }
}