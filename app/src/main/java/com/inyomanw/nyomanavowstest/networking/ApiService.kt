package com.inyomanw.nyomanavowstest.networking

import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.data.response.CartsResponse
import com.inyomanw.nyomanavowstest.data.response.CategoryResponse
import com.inyomanw.nyomanavowstest.data.response.LoginResponse
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.response.ProductResponse
import com.inyomanw.nyomanavowstest.data.response.TestUsers
import com.inyomanw.nyomanavowstest.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("products")
    suspend fun getListProducts(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getDetailProduct(@Path("id") id: Int): Response<Product>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getListProductByCategory(@Path("category") category: String): Response<List<Product>>

    @GET("carts/user/{id}")
    suspend fun getUserCarts(@Path("id") id: Int): Response<List<CartsResponse>>

    @POST("carts")
    suspend fun addCart(@Body cartRequest: CartRequest): Response<CartsResponse>

    @PUT("carts/{id}")
    suspend fun updateCart(
        @Path("id") id: Int,
        @Body cartRequest: CartRequest
    ): Response<CartsResponse>

    @DELETE("carts/{id}")
    suspend fun deleteCart(
        @Path("id") id: Int,
        @Body cartRequest: CartRequest
    ): Response<CartsResponse>
}
