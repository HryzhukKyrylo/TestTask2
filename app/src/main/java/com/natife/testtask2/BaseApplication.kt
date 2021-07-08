package com.natife.testtask2

import android.app.Application
import com.natife.testtask2.di.localModule
import com.natife.testtask2.di.networkModule
import com.natife.testtask2.di.repositoryModule
import com.natife.testtask2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                networkModule,
                localModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
