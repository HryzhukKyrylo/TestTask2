package com.natife.testtask2.di

import android.content.Context
import com.natife.testtask2.data.local.AppDatabase
import com.natife.testtask2.data.local.UserDao
import com.natife.testtask2.data.remote.RetrofitService
import com.natife.testtask2.data.repository.MainRepository
import com.natife.testtask2.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *@author admin
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService = retrofit.create(
        RetrofitService::class.java
    )

    @Provides
    @Singleton
    fun provideGiphyRepository(service: RetrofitService): MainRepository =
        MainRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase(appContext)

    @Provides
    @Singleton
    fun provideUserDao( db: AppDatabase) = db.userDao()
    //https://itnext.io/android-architecture-hilt-mvvm-kotlin-coroutines-live-data-room-and-retrofit-ft-8b746cab4a06

}
