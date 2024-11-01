package com.inyomanw.nyomanavowstest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.inyomanw.nyomanavowstest.data.room.table.ProductTable
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.data.room.table.RatingProductTable
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productTable: ProductTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productTables: List<ProductTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: RatingProductTable)

    @Query("SELECT * FROM product")
    suspend fun getAllProduct(): List<ProductTable>

    @Query("SELECT * FROM product WHERE idProduct = :id")
    fun getProductById(id: Int): Flow<ProductTable?>

    @Transaction
    @Query("""
        SELECT * FROM product 
        INNER JOIN ratingProduct ON product.ratingId = ratingProduct.idRating
    """)
    fun getProductsWithRatings(): Flow<List<ProductWithRating>>


}