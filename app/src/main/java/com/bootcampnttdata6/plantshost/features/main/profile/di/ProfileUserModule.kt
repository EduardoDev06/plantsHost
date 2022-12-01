package com.bootcampnttdata6.plantshost.features.main.profile.di

import com.bootcampnttdata6.plantshost.features.main.profile.data.repository.ProfileUserRepositoryImpl
import com.bootcampnttdata6.plantshost.features.main.profile.domain.repository.ProfileUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileUserModule {
    @Binds
    @Singleton
    abstract fun bindProfileUserRepository(ProfileUserRepositoryImpl: ProfileUserRepositoryImpl): ProfileUserRepository
}