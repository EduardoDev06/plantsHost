package com.bootcampnttdata6.plantshost.features.main.home.di

import com.bootcampnttdata6.plantshost.features.main.home.data.repository.PlantsRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.home.domain.repository.PlantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPlantsRepository(plantsRepositoryImpl: PlantsRepositoryImpl) : PlantsRepository
}