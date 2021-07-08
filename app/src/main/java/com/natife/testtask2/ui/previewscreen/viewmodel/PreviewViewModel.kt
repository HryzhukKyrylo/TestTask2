package com.natife.testtask2.ui.previewscreen.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.natife.testtask2.BaseApplication
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.repository.GlobalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PreviewViewModel @Inject constructor(
    var repository: GlobalRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun fetchUser(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(repository.findUserById(id))
        }
    }
}
