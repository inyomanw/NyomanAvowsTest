package com.inyomanw.nyomanavowstest.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _isLoading = MutableStateFlow(false)
    protected val _isSuccess = MutableStateFlow(false)
    protected val _errorMessage = MutableStateFlow("")
    val isLoading: StateFlow<Boolean> get() = _isLoading
    val errorMessage: StateFlow<String> get() = _errorMessage
    val isSuccess: StateFlow<Boolean> get() = _isSuccess

    protected fun <T> ResourceState<T>.onResult(
        action: (T) -> Unit,
        actionError: (String) -> Unit,
        onNullData: () -> Unit = { kotlin.run { } }
    ) {
        when (this@onResult) {
            is ResourceState.Success -> {
                _isLoading.value = false
                this@onResult.data?.let {
                    action.invoke(it)
                } ?: kotlin.run {
                    onNullData.invoke()
                }
            }
            is ResourceState.Error -> {
                _isLoading.value = false
                this@onResult.errorMsg?.let { actionError.invoke(it) }
            }
        }
    }

}