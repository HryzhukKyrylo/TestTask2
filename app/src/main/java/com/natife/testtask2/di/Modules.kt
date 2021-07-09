package com.natife.testtask2.di

import android.app.Application
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
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
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

    factory { provideLoggingInterceptor() }
    factory { provideRetrofit(get()) }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofitService(get()) }
}

val localModule = module {

    fun provideDatabase(application: Application) =
        AppDatabase.getDatabase(application.applicationContext)

    fun provideUserDao(db: AppDatabase) = db.userDao()

    factory { provideDatabase(androidApplication()) }
    factory { provideUserDao(get()) }
}

val repositoryModule = module {

    fun provideLocalRepository(dao: HumanDao) = LocalRepository(dao)

    fun provideRemoteRepository(api: ApiService) = RemoteRepository(api)

    fun provideGlobalLocalRepository(local: LocalRepository): GlobalRepository {
        return local
    }

    fun provideGlobalRemoteRepository(remote: RemoteRepository): GlobalRepository {
        return remote
    }

    fun provideMainRepositoryDecorator(
        local: GlobalRepository,
        remote: GlobalRepository
    ): GlobalRepository {
        return MainRepositoryDecorator(remote, local)
    }

    factory { provideLocalRepository(get()) }

    factory { provideRemoteRepository(get()) }

    factory (Local) {
        provideGlobalLocalRepository(get())
    }
    factory (Remote) {
        provideGlobalRemoteRepository(get())
    }
    single {
        provideMainRepositoryDecorator(
            get(Local),
            get(Remote)
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

object Local : Qualifier // define your own qualifier
{
    override val value: QualifierValue
        get() = "Local"
}
object Remote : Qualifier // define your own qualifier
{
    override val value: QualifierValue
        get() = "Re,ote"
}
