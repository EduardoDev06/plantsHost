package com.bootcampnttdata6.plantshost.features.main.home.domain.usecase


import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.domain.repository.PlantsRepository
import com.bootcampnttdata6.plantshost.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantsUseCase @Inject constructor(private val plantsRepository: PlantsRepository) {
    operator fun invoke(): Flow<Resource<List<Plants>>> = plantsRepository.fetchListPlants()
}