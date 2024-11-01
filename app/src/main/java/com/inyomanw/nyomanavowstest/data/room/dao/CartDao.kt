package com.inyomanw.nyomanavowstest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.inyomanw.nyomanavowstest.data.room.table.CartProductTable
//import com.inyomanw.nyomanavowstest.data.room.table.CartTable
import com.inyomanw.nyomanavowstest.data.room.table.ProductTable
import com.inyomanw.nyomanavowstest.data.room.table.RatingProductTable

//import com.inyomanw.nyomanavowstest.data.room.table.CartTable

@Dao
interface CartDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCart(cart: CartTable)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCartProduct(cartProduct: CartProductTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: RatingProductTable)

}