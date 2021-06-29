package com.natife.testtask2.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.UserResponse
import com.natife.testtask2.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *@author admin
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _users = MutableLiveData<UserResponse>()
    val users : LiveData<UserResponse> = _users
    val errorMessage = MutableLiveData<String>()

    fun getUsers() = viewModelScope.launch{
        withContext(Dispatchers.IO) {
            val response = repository.getAllUser()
            _users.postValue(response)
        }
    }
}
