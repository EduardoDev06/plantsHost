package com.bootcampnttdata6.plantshost.features.main.favorite.data.repository

import com.bootcampnttdata6.plantshost.core.data.local.dao.PlantsDao
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlantsFavoriteRepositoryImpl @Inject constructor(
    private val plantsDao: PlantsDao
): PlantFavoriteRepository {

    override fun getPlantsFavorite(): Flow<List<Plants>> = flow {
        plantsDao.getPlants().map{ plants->  plants.map {it.toPlantsDomain() } }.collect{
            emit(it)
        }
    }

}