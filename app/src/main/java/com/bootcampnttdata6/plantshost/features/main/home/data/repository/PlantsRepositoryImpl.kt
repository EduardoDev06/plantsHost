package com.bootcampnttdata6.plantshost.features.main.home.data.repository

import com.bootcampnttdata6.plantshost.core.data.local.dao.PlantsDao
import com.bootcampnttdata6.plantshost.core.data.remote.api.ApiService
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.domain.repository.PlantsRepository
import com.bootcampnttdata6.plantshost.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class PlantsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val plantsDao: PlantsDao
) :
    PlantsRepository {

    override fun fetchListPlants(): Flow<Resource<List<Plants>>> = flow {
        emit(Resource.Loading)
        try {
            plantsDao.getPlants().collect { plants ->
                if (plants.isEmpty()) {
                    val result = apiService.fetchListPlants()
                    plantsDao.insertAllPlants(result.plants.map { it.toPlantsEntity() })
                    emit(Resource.Success(result.plants.map { it.toPlantsDomain() }))
                } else {
                    emit(Resource.Success(plants.map { it.toPlantsDomain() }))
                }
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.message.toString()))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.message.toString()))
        }
    }

}

