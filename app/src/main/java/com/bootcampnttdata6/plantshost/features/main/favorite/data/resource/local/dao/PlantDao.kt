package com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.dao

import androidx.room.*
import com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.entity.PlantsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM PlantsEntity")
    fun getPlants(): Flow<List<PlantsEntity>>

}