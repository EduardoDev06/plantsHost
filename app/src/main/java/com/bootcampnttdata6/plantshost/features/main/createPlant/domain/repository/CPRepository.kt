package com.bootcampnttdata6.plantshost.features.main.createPlant.domain.repository
import android.net.Uri
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.util.Resource
import kotlinx.coroutines.flow.Flow

interface CPRepository {
    fun insertNewPlant(plants: PlantsEntity)
    fun addImageToFStorage(name:String,imageUri: Uri):Flow<Resource<Uri>>
}