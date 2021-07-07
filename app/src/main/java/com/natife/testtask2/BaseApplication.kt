package com.natife.testtask2

import android.app.Application
import com.natife.testtask2.di.module.AppModule
import com.natife.testtask2.di.component.AppComponent
import com.natife.testtask2.di.component.DaggerAppComponent


class BaseApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent{
        return appComponent
    }
}
