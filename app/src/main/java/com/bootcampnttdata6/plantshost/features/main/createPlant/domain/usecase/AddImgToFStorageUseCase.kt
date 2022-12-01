package com.bootcampnttdata6.plantshost.features.main.createPlant.domain.usecase
import android.net.Uri
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.repository.CPRepository
import com.bootcampnttdata6.plantshost.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddImgToFStorageUseCase @Inject constructor(
    private val fStorageRepository: CPRepository
    ) {
        operator fun invoke(name:String,imageUri:Uri):Flow<Resource<Uri>> = fStorageRepository.addImageToFStorage(name,imageUri)
    }