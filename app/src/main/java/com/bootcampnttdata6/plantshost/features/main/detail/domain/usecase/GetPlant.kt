package com.bootcampnttdata6.plantshost.features.main.detail.domain.usecase

import com.bootcampnttdata6.plantshost.features.main.detail.domain.model.Plant
import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import javax.inject.Inject

class GetPlant @Inject constructor(
    private val repository: PlantRepository
){
    operator fun invoke(id: Int): Plant? {
        return repository.getPlantById(id)

    }