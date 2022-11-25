package com.bootcampnttdata6.plantshost.features.auth.sign_in.di

import com.bootcampnttdata6.plantshost.features.auth.sign_in.data.remote.AuthRepositoryImpl
import com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Repositorymodule {
    @Binds
    abstract fun binAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}