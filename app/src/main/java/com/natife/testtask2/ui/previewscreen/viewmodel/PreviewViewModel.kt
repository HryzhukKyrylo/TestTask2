package com.natife.testtask2.ui.previewscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
    private val repository: GlobalRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user = _user

    fun fetchUser(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _user.postValue(repository.findUserById(id))
            }
        }
    }
}
