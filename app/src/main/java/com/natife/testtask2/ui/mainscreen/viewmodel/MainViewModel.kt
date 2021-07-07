package com.natife.testtask2.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.utils.Const
import com.natife.testtask2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GlobalRepository
) : ViewModel() {

    private val _responseUsers = MutableLiveData<Resource<List<User>>>()
    val responseUsers: LiveData<Resource<List<User>>> = _responseUsers

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _responseUsers.postValue(Resource.loading())
            try {
                _responseUsers.postValue(repository.loadUsers())
            } catch (exception: Exception) {
                _responseUsers.postValue(
                    Resource.error(
                        message = exception.message ?: Const.ERROR_LOAD_USERS
                    )
                )
            }
        }
    }
}
