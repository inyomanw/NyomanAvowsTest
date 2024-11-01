package com.inyomanw.nyomanavowstest.domain.repository

import com.inyomanw.nyomanavowstest.base.ResourceState
import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.data.response.CartsResponse
import com.inyomanw.nyomanavowstest.data.response.LoginResponse
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.response.UserResponse
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.data.room.table.UserTable
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun login(loginRequest: LoginRequest) : ResourceState<LoginResponse>

    suspend fun getAllUser(): ResourceState<List<UserResponse>>

    suspend fun getProducts(): ResourceState<List<Product>>

    suspend fun getDetailProduct(id: Int): ResourceState<Product>

    suspend fun getCategories(): ResourceState<List<String>>

    suspend fun getListProductByCategory(category: String): ResourceState<List<Product>>

    suspend fun getUserCarts(id: Int): ResourceState<List<CartsResponse>>

    suspend fun addCart(cartRequest: CartRequest): ResourceState<CartsResponse>

    suspend fun updateCart(id: Int, cartRequest: CartRequest): ResourceState<CartsResponse>

    suspend fun deleteCart(id: Int, cartRequest: CartRequest): ResourceState<CartsResponse>

    suspend fun saveUser(userTable: UserTable)

    fun getUserByEmail(email:String) : Flow<UserTable?>

//    fun getUserByUsername(username:String) : UserWithAddress

    suspend fun getUserByUsername(username:String) : UserWithAddress

    fun getProductsChart() : Flow<List<ProductWithRating>>

    suspend fun addChart(detailProduct: Product)
}