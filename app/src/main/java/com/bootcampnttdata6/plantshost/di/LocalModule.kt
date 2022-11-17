package com.bootcampnttdata6.plantshost.di

import android.app.Application
import androidx.room.Room
import com.bootcampnttdata6.plantshost.core.HostPlantsDB
import com.bootcampnttdata6.plantshost.features.main.favorite.data.repository.PlantsFavoriteRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.repository.PlantFavoriteRepository
import com.bootcampnttdata6.plantshost.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        HostPlantsDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: HostPlantsDB): PlantFavoriteRepository {
        return PlantsFavoriteRepositoryImpl(db.plantsDao)
    }

}