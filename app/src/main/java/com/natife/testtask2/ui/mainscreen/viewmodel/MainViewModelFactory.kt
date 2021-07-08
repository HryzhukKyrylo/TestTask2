package com.natife.testtask2.ui.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MainViewModelFactory @Inject constructor(private val viewModel: MutableMap<Class<out ViewModel>,Provider<ViewModel>>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModel[modelClass]?.get() as T
}
