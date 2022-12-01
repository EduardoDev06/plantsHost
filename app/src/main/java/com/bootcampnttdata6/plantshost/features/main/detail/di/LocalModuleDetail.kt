package com.bootcampnttdata6.plantshost.features.main.detail.di

import com.bootcampnttdata6.plantshost.features.main.detail.data.repository.PlantRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.detail.domain.repository.PlantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalModuleDetail {
    @Binds
    abstract fun bindPlantsDetailRepositoryImpl(impl: PlantRepositoryImpl) : PlantRepository
}
