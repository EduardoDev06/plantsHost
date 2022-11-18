package com.bootcampnttdata6.plantshost.features.main.detail.data.repository

import com.bootcampnttdata6.plantshost.features.main.detail.data.resource.dao.PlantDao
import com.bootcampnttdata6.plantshost.features.main.detail.domain.model.Plant
import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val dao: PlantDao
): PlantRepository {
    override fun getPlantById(id:Int): Plant? {
        return dao.getPlantById(id)
    }
}