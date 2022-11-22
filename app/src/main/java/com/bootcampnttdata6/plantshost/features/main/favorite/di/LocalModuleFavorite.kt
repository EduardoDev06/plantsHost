package com.bootcampnttdata6.plantshost.features.main.favorite.di

import com.bootcampnttdata6.plantshost.features.main.favorite.data.repository.PlantsFavoriteRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalModuleFavorite {

    @Binds
    abstract fun bindPlantsFavoriteRepositoryImpl(impl: PlantsFavoriteRepositoryImpl) : PlantFavoriteRepository

}