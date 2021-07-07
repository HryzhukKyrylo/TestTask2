package com.natife.testtask2.di.module

import com.natife.testtask2.MainActivity
import com.natife.testtask2.ui.mainscreen.MainScreenFragment
import com.natife.testtask2.ui.previewscreen.PreviewScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *@author admin
 */
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector( modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun  contributeMyMainFragment(): MainActivity
}
