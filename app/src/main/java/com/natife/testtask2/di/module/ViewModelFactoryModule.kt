package com.natife.testtask2.di.module

import androidx.lifecycle.ViewModelProvider
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
}
