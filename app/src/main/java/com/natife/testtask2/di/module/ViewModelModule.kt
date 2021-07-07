package com.natife.testtask2.di.module

import androidx.lifecycle.ViewModel
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.utils.ViewModelKey
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *@author admin
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}