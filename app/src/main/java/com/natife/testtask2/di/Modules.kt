package com.natife.testtask2.di

import android.app.Application
import android.content.Context
import com.natife.testtask2.BuildConfig
import com.natife.testtask2.data.local.AppDatabase
import com.natife.testtask2.data.remote.ApiService
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.LocalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import com.natife.testtask2.data.repository.RemoteRepository
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author admin
 */
val networkModule = module {
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()

    fun provideRetrofitService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )

    single { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofitService(get()) }
}

val localModule = module {
    fun provideContext(application: Application) =
        application.applicationContext

    fun provideDatabase(appContext: Context) =
        AppDatabase.getDatabase(appContext)

    fun provideUserDao(db: AppDatabase) = db.userDao()

    single { provideContext(androidApplication()) }
    single { provideDatabase(get()) }
    single { provideUserDao(get()) }
}

val repositoryModule = module {
    factory {

    }
    single<GlobalRepository>(named("local")) {

    }
    single<GlobalRepository>(named("remote")) {

    }
    single<GlobalRepository> {
        MainRepositoryDecorator(get(named("remote"), get(named("local"))))
    }

    fun bindLocalRetrofit(local: LocalRepository): GlobalRepository {
        return local
    }

    fun bindRemoteRetrofit(remote: RemoteRepository): GlobalRepository {
        return remote
    }

    fun provideMainRepositoryDecorator(
        local: GlobalRepository,
        remote: GlobalRepository
    ): GlobalRepository {
        return MainRepositoryDecorator(remote, local)
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
}
