package com.bootcampnttdata6.plantshost.di

import com.bootcampnttdata6.plantshost.core.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private const val BASE_URL = "https://planthost-d4cdc-default-rtdb.firebaseio.com/"
    @Provides
    @Singleton
    fun provideRetrofit(/*client: OkHttpClient*/): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}