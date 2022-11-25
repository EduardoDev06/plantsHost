package com.bootcampnttdata6.plantshost.features.main.createPlant.domain.usecase
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.repository.CPRepository
import javax.inject.Inject

class InsertPlantUseCase @Inject constructor(
    private val plantRepository: CPRepository
    ) {
        operator fun invoke(plants:PlantsEntity)= plantRepository.insertNewPlant(plants)
    }