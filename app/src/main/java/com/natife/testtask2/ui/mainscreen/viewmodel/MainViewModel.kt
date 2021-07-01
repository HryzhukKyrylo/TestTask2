package com.natife.testtask2.ui.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.repository.MainLocalRepository
import com.natife.testtask2.data.repository.MainRemoteRepository
import com.natife.testtask2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRemoteRepository
) : ViewModel() {
    private val _responseUsers = MutableLiveData<Resource<List<Human>>>()
    val responseUsers = _responseUsers

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _responseUsers.postValue(Resource.loading(data = null))
                try {
                    val result = repository.loadUsers()
                    _responseUsers.postValue(Resource.success(data = result))
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
