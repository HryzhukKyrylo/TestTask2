package com.natife.testtask2.ui.previewscreen.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.repository.GlobalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreviewViewModel (
    private val repository: GlobalRepository,
    private val id: String
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        fetchUser()
    }
    fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(repository.findUserById(id))
        }
    }
}
