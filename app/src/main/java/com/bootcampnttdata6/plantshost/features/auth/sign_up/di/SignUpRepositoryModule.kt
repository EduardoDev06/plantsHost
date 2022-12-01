package com.bootcampnttdata6.plantshost.features.auth.sign_up.di

import com.bootcampnttdata6.plantshost.features.auth.sign_up.data.SignUpRepositoryImpl
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpRepositoryModule {
    @Binds
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}