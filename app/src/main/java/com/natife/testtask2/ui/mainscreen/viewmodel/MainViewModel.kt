package com.natife.testtask2.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.UserResponse
import com.natife.testtask2.data.repository.MainRepository
import com.natife.testtask2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _responseUsers = MutableLiveData<Resource<UserResponse>>()
    val responseUsers: LiveData<Resource<UserResponse>> = _responseUsers

    fun getUsers() {
        loadUsers(false)
    }

    fun loadMoreUsers() {
        loadUsers(true)
    }

    fun loadUsers(loadNext: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _responseUsers.postValue(Resource.loading(data = null))
                try {
                    val result = repository.loadHumans(loadNext)
                    _responseUsers.postValue(result)

                } catch (exception: Exception) {
                    _responseUsers.postValue(
                        Resource.error(
                            data = null,
                            message = exception.message ?: "WTF Error"
                        )
                    )
                }
            }
        }
    }
}
