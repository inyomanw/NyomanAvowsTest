package com.inyomanw.nyomanavowstest.presentation.cart

import androidx.lifecycle.viewModelScope
import com.inyomanw.nyomanavowstest.base.BaseViewModel
import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.response.CartsResponse
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository): BaseViewModel() {

    private val _carts = MutableStateFlow<List<CartsResponse>>(emptyList())
    val carts: StateFlow<List<CartsResponse>> get() = _carts

    val listCart :Flow<List<ProductWithRating>> = repository.getProductsChart()

    fun getUserCarts(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getUserCarts(id).onResult(
                {
                    _carts.value = it
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun updateCarts(id: Int, cartRequest: CartRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.updateCart(id, cartRequest).onResult(
                {
                    _isSuccess.value = true
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun deleteCarts(id: Int, cartRequest: CartRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteCart(id, cartRequest).onResult(
                {
                    _isSuccess.value = true
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }
}