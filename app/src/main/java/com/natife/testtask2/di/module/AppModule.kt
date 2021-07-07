package com.natife.testtask2.di.module

import android.app.Application
import android.content.Context
import com.natife.testtask2.BuildConfig
import com.natife.testtask2.data.local.AppDatabase
import com.natife.testtask2.data.remote.ApiService
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.LocalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import com.natife.testtask2.data.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Named(LOCAL)
    fun bindLocalRetrofit(local: LocalRepository): GlobalRepository {
        return local
    }

    @Provides
    @Named(REMOTE)
    fun bindRemoteRetrofit(remote: RemoteRepository): GlobalRepository {
        return remote
    }

    @Provides
    fun provideMainRepositoryDecorator(
        @Named(LOCAL) local: GlobalRepository,
        @Named(REMOTE) remote: GlobalRepository
    ): GlobalRepository {
        return MainRepositoryDecorator(remote, local)
    }

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
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.userDao()


    companion object {
        const val LOCAL = "local"
        const val REMOTE = "remote"
    }
}
