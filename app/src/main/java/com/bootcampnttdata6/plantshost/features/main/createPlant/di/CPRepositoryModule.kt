package com.bootcampnttdata6.plantshost.features.main.createPlant.di
import com.bootcampnttdata6.plantshost.features.main.createPlant.data.repository.CPRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.repository.CPRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CPRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCPRepository(fStorageRepositoryImpl: CPRepositoryImpl) : CPRepository
}