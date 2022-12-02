package com.bootcampnttdata6.plantshost.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantsDao {

    @Query("SELECT * FROM PlantsEntity ORDER BY id DESC")
    fun getPlants(): Flow<List<PlantsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlants(plants : List<PlantsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPlant(plant: PlantsEntity)

    @Query("SELECT * FROM PlantsEntity WHERE id = :id")
    suspend fun getPlantById(id:Int) : Plants?

    @Query("UPDATE PlantsEntity SET isFavorite = :isFavorite WHERE id= :id")
    suspend fun changeFav(isFavorite : Boolean, id: Int)

}