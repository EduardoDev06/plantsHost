package com.bootcampnttdata6.plantshost.core.data.remote.api

import com.bootcampnttdata6.plantshost.core.data.remote.dto.PlantsDto
import retrofit2.http.GET

interface ApiService {
    @GET("Plants/-NGwvNU-Qr81thuLKkim.json")
    suspend fun fetchListPlants(): PlantsDto
}