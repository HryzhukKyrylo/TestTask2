package com.natife.testtask2.di.component

import com.natife.testtask2.di.module.PreviewViewModelModule
import com.natife.testtask2.ui.previewscreen.viewmodel.PreviewViewModel
import dagger.BindsInstance
import dagger.Subcomponent

/**
 *@author admin
 */
@Subcomponent( modules = [ PreviewViewModelModule::class])
interface PreviewViewModelComponent {
    val previewViewModel: PreviewViewModel
    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance id:String):PreviewViewModelComponent
    }
}
