package com.bootcampnttdata6.plantshost.features.main.detail.domain.usecase

import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import javax.inject.Inject

class GetPlant @Inject constructor(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(id: Int): Plants? {
        return repository.getPlantById(id)
    }
}