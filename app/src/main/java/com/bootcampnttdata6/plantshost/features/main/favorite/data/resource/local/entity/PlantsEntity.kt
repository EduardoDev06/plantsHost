package com.bootcampnttdata6.plantshost.features.main.favorite.data.resource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel

@Entity
data class PlantsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val nameplant: String,
    @ColumnInfo val price: String,
    @ColumnInfo val status: Int,
    @ColumnInfo val image: String,
    @ColumnInfo val description: String,
){

    fun toPlantModel() = PlantModel(
        id = id,
        nameplant = nameplant,
        price = price,
        status = status,
        image = image,
        description = description,
        isFavorite = false
    )

}