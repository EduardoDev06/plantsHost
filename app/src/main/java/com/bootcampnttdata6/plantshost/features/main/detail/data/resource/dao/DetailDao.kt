package com.bootcampnttdata6.plantshost.features.main.detail.data.resource.dao

import androidx.room.Dao
import androidx.room.Query
import com.bootcampnttdata6.plantshost.features.main.detail.domain.model.Plant

@Dao
interface PlantDao {
    @Query(“SELECT * FROM PlantsEntity WHERE id = :id”)
    suspended fun getPlantById(id:Int) : Plant?
}