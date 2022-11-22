package com.bootcampnttdata6.plantshost.di

import android.content.Context
import androidx.room.Room
import com.bootcampnttdata6.plantshost.core.data.local.db.HostPlantsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    private const val DATABASE_NAME = "PlantsHots_db"
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        HostPlantsDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: HostPlantsDB) = db.plantsDao()
}