package com.natife.testtask2.di.module

import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.ui.previewscreen.viewmodel.PreviewViewModel
import dagger.Module
import dagger.Provides

/**
 *@author admin
 */
@Module
class PreviewViewModelModule {
    @Provides
    fun providePreviewViewModel(repository: GlobalRepository, id: String ):PreviewViewModel{
        return PreviewViewModel(repository = repository, id = id)
    }
}
