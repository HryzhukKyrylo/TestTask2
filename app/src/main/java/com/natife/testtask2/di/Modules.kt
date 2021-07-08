package com.natife.testtask2.di

import android.app.Application
import android.content.Context
import com.natife.testtask2.BuildConfig
import com.natife.testtask2.data.local.AppDatabase
import com.natife.testtask2.data.local.HumanDao
import com.natife.testtask2.data.remote.ApiService
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.LocalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import com.natife.testtask2.data.repository.RemoteRepository
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.ui.previewscreen.viewmodel.PreviewViewModel
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

    fun provideDatabase(application: Application) =
        AppDatabase.getDatabase(application.applicationContext)

    fun provideUserDao(db: AppDatabase) = db.userDao()

    single { provideDatabase(androidApplication()) }
    single { provideUserDao(get()) }
}

val repositoryModule = module {

    fun provideLocalRepository(dao: HumanDao) = LocalRepository(dao)

    fun provideRemoteRepository(api: ApiService) = RemoteRepository(api)

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

    single { provideLocalRepository(get()) }

    single { provideRemoteRepository(get()) }

    single<GlobalRepository>(named("local")) {
        bindLocalRetrofit(get())
    }
    single<GlobalRepository>(named("remote")) {
        bindRemoteRetrofit(get())
    }
    single<GlobalRepository> {
        provideMainRepositoryDecorator(
            get(named("local")),
            get(named("remote"))
        )
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
    viewModel { parameters ->
        PreviewViewModel(id = parameters.get(),repository = get())
    }

}
