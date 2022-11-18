package com.bootcampnttdata6.plantshost.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bootcampnttdata6.plantshost.core.data.local.dao.PlantsDao
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity


@Database(
    entities = [PlantsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HostPlantsDB : RoomDatabase() {
    abstract fun plantsDao(): PlantsDao
}
