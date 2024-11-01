package com.inyomanw.nyomanavowstest.presentation.login

import androidx.lifecycle.viewModelScope
import com.inyomanw.nyomanavowstest.base.BaseViewModel
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.data.response.LoginResponse
import com.inyomanw.nyomanavowstest.data.response.UserResponse
import com.inyomanw.nyomanavowstest.data.room.table.UserTable
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import com.inyomanw.nyomanavowstest.domain.mapper.toUserTable
import com.inyomanw.nyomanavowstest.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get() = _loginResponse

    private val _users = MutableStateFlow<UserResponse?>(null)
    val users: StateFlow<UserResponse?> get() = _users

    private val _userWithAddress = MutableStateFlow<UserWithAddress?>(null)
    val userWithAddress = _userWithAddress

    fun getUserByUsername(username: String) {
        viewModelScope.launch {
           _userWithAddress.value = repository.getUserByUsername(username)
        }

    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.login(loginRequest).onResult(
                {
                    _loginResponse.value = it
                    _isSuccess.value = true
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun getUser(userName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllUser().onResult(
                {
                   val username= it.find { user ->
                        user.username == userName
                    }
                  getUserByUsername(userName)
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }
}
