package com.bootcampnttdata6.plantshost.features.main.home.domain.repository

import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {

    fun fetchListPlants(): Flow<Resource<List<Plants>>>
    fun getListPlants(): Flow<List<Plants>>

}