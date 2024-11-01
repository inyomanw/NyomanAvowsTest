package com.inyomanw.nyomanavowstest.presentation.products

import androidx.lifecycle.viewModelScope
import com.inyomanw.nyomanavowstest.base.BaseViewModel
import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import com.inyomanw.nyomanavowstest.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: Repository): BaseViewModel() {

    private val _productsResponse = MutableStateFlow<List<Product>>(emptyList())
    val productResponse: StateFlow<List<Product>> get() = _productsResponse

    private val _detailProduct = MutableStateFlow<Product?>(null)
    val detailProduct: StateFlow<Product?> get() = _detailProduct

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> get() = _categories

    private val _userWithAddress = MutableStateFlow<UserWithAddress?>(null)
    val userWithAddress = _userWithAddress

    val  list = repository.getProductsChart()
    fun getProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getProducts().onResult(
                {
                    _productsResponse.value = it
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun getDetailProduct(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getDetailProduct(id).onResult(
                {
                    _detailProduct.value = it
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getCategories().onResult(
                {
                    _categories.value = it
                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun getUserByUsername(username: String) {
        viewModelScope.launch {
            userWithAddress.value = repository.getUserByUsername(username)
        }

    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getListProductByCategory(category).onResult(
                {
                    _productsResponse.value = it

                },
                {
                    _errorMessage.value = it
                }
            )
        }
    }

    fun addCart(cartRequest: CartRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _detailProduct.value?.let { repository.addChart(it) }
            repository.addCart(cartRequest).onResult(
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