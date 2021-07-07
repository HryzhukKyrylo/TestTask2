package com.natife.testtask2.di.component

import com.natife.testtask2.di.module.AppModule
import com.natife.testtask2.ui.mainscreen.MainScreenFragment
import com.natife.testtask2.ui.mainscreen.viewmodel.MainViewModel
import com.natife.testtask2.ui.previewscreen.viewmodel.PreviewViewModel
import dagger.Component
import javax.inject.Singleton

//@Singleton
//@Component(modules = [AppModule::class])
//interface AppComponent {
//    fun inject(mainScreenFragment: MainScreenFragment)
//    fun inject(previewViewModel: PreviewViewModel)
//}
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(previewViewModel: PreviewViewModel)
}
