package com.natife.testtask2.di.component

import android.app.Application
import com.natife.testtask2.BaseApplication
import com.natife.testtask2.di.module.ActivityBuildersModule
import com.natife.testtask2.di.module.AppModule
import com.natife.testtask2.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}
