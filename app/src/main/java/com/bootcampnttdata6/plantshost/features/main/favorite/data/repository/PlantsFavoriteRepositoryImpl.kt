package com.bootcampnttdata6.plantshost.features.main.favorite.data.repository

import com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.dao.PlantDao
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlantsFavoriteRepositoryImpl @Inject constructor(
    private val dao: PlantDao
): PlantFavoriteRepository {

    override fun getPlants(): Flow<List<PlantModel>> {
        return dao.getPlants().map { it.map { plant -> plant.toPlantModel() } }
    }

}