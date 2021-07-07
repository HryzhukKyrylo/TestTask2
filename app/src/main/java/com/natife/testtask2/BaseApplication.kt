package com.natife.testtask2

import com.natife.testtask2.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }


}
