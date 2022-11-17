package com.bootcampnttdata6.plantshost.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.dao.PlantDao
import com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.entity.PlantsEntity


@Database(
    entities = [PlantsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HostPlantsDB: RoomDatabase() {
    abstract val plantsDao: PlantDao
}