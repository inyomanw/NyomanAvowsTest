package com.inyomanw.nyomanavowstest.domain.repository

import com.inyomanw.nyomanavowstest.base.ResourceState
import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.data.response.CartsResponse
import com.inyomanw.nyomanavowstest.data.response.LoginResponse
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.response.UserResponse
import com.inyomanw.nyomanavowstest.data.room.dao.CartDao
import com.inyomanw.nyomanavowstest.data.room.dao.CategoryDao
import com.inyomanw.nyomanavowstest.data.room.dao.ProductDao
import com.inyomanw.nyomanavowstest.data.room.table.UserTable
import com.inyomanw.nyomanavowstest.data.room.dao.UserDao
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import com.inyomanw.nyomanavowstest.domain.mapper.toAddressTable
//import com.inyomanw.nyomanavowstest.domain.mapper.toCartTable
import com.inyomanw.nyomanavowstest.domain.mapper.toCategoryTable
import com.inyomanw.nyomanavowstest.domain.mapper.toProductTable
import com.inyomanw.nyomanavowstest.domain.mapper.toRatingProductTable
//import com.inyomanw.nyomanavowstest.domain.mapper.toProductTable
import com.inyomanw.nyomanavowstest.domain.mapper.toUserTable
import com.inyomanw.nyomanavowstest.networking.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class RepositoryImplementation @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val productDao: ProductDao,
//    private val cartDao: CartDao,
    private val categoryDao: CategoryDao
) : Repository {

    private suspend fun <T> getResult(
        request: suspend () ->
        Response<T>
    ): ResourceState<T> {
        return try {
            val body = request().body()
            ResourceState.Success(body)
        } catch (e: Exception) {
            ResourceState.Error(msg = e.message.toString())
        }
    }

    private suspend fun <T> suspendDataResult(request: suspend () -> ResourceState<T>): ResourceState<T> {
        return withContext(Dispatchers.IO) {
            request.invoke()
        }
    }


    override suspend fun login(loginRequest: LoginRequest): ResourceState<LoginResponse> =
        suspendDataResult {
            getResult { apiService.login(loginRequest) }
        }

    override suspend fun getAllUser(): ResourceState<List<UserResponse>> = suspendDataResult {
        getResult {
            val users = apiService.getUsers()
            users.body()?.map {
                userDao.insertAddress(it.address.toAddressTable())
                it.toUserTable()
            }?.let {
                userDao.insertAll(it)
            }
            users
        }
    }

    override suspend fun getProducts(): ResourceState<List<Product>> = suspendDataResult {
        getResult {
            val products = apiService.getListProducts()
//            products.body()?.map {
//                it.toProductTable()
//            }?.let { productDao.insertAll(it) }
            products
        }
    }

    override suspend fun getDetailProduct(id: Int) = suspendDataResult {
        getResult { apiService.getDetailProduct(id) }
    }

    override suspend fun getCategories(): ResourceState<List<String>> = suspendDataResult {
        getResult {
            val category = apiService.getCategories()
//            category.body()?.map {
//                it.toCategoryTable()
//            }?.let { categoryDao.insertAll(it) }
            category
        }
    }

    override suspend fun getListProductByCategory(category: String): ResourceState<List<Product>> =
        suspendDataResult {
            getResult { apiService.getListProductByCategory(category) }
        }

    override suspend fun getUserCarts(id: Int): ResourceState<List<CartsResponse>> = suspendDataResult {
        getResult {
            val carts = apiService.getUserCarts(id)
//            carts.body()?.map {
//                it.toCartTable()
//            }?.let {
//
//            }
            carts
        }
    }

    override suspend fun addCart(cartRequest: CartRequest): ResourceState<CartsResponse> =
        suspendDataResult {
            getResult { apiService.addCart(cartRequest) }
        }

    override suspend fun updateCart(
        id: Int,
        cartRequest: CartRequest
    ): ResourceState<CartsResponse> = suspendDataResult {
        getResult { apiService.updateCart(id, cartRequest) }
    }

    override suspend fun deleteCart(
        id: Int,
        cartRequest: CartRequest
    ): ResourceState<CartsResponse> = suspendDataResult {
        getResult { apiService.deleteCart(id, cartRequest) }
    }

    override suspend fun saveUser(userTable: UserTable) {
        userDao.insert(userTable)
    }

    override fun getUserByEmail(email:String): Flow<UserTable?> {
       return userDao.getUserByEmail(email)
    }

    override  suspend fun getUserByUsername(username: String): UserWithAddress {
        return userDao.getUserByUsername(username)
    }

    override fun getProductsChart(): Flow<List<ProductWithRating>> {
        return productDao.getProductsWithRatings()
    }

    override suspend fun addChart(detailProduct: Product) {
        val productData = detailProduct.toProductTable()
        val rateData = detailProduct.rating.toRatingProductTable(productData.id)
        productDao.insert(productData)
        productDao.insertRating(rateData)
    }
}