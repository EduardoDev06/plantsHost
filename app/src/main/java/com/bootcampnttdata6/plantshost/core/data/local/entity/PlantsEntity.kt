package com.bootcampnttdata6.plantshost.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants

@Entity
data class PlantsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val description: String,
    @ColumnInfo val nameplant: String,
    @ColumnInfo val image: String,
    @ColumnInfo val price: String,
    @ColumnInfo val status: String,
    @ColumnInfo var isFavorite: Boolean

) {

    fun toPlantsDomain() = Plants(
        id = id,
        description = description,
        image = image,
        nameplant= nameplant,
        price = price,
        status = status,
        isFavorite = false
    )
}