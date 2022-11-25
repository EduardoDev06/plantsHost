package com.bootcampnttdata6.plantshost.features.main.createPlant.data.repository
import android.net.Uri
import com.bootcampnttdata6.plantshost.core.data.local.dao.PlantsDao
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.repository.CPRepository
import com.bootcampnttdata6.plantshost.util.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CPRepositoryImpl @Inject constructor(
    private val plantsDao: PlantsDao
):
    CPRepository {
        private val bucket: String = "gs://planthost-d4cdc.appspot.com"
        private val storageRef = Firebase.storage(bucket).reference

    override fun insertNewPlant(plants: PlantsEntity) {
        plantsDao.insertNewPlant(plants)
    }

    override fun addImageToFStorage(name:String,imageUri: Uri):Flow<Resource<Uri>> = flow{
            try{
                emit(Resource.Loading)
                val downloadUrl = storageRef.child("/plants/$name")
                    .putFile(imageUri).await()
                    .storage.downloadUrl.await()
                emit(Resource.Success(downloadUrl))
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString()))
            }
        }
    }