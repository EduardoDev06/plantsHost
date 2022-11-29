package com.bootcampnttdata6.plantshost.features.main.detail.data.repository

import com.bootcampnttdata6.plantshost.core.data.local.dao.PlantsDao
import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val dao: PlantsDao
): PlantRepository {
    override suspend fun getPlantById(id:Int): Plants? {
        return dao.getPlantById(id)
    }

    override suspend fun changeFav(id: Int, favorite: Boolean) {
        dao.changeFav(favorite,id)
    }
}