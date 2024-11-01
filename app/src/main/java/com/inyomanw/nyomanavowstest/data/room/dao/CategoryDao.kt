package com.inyomanw.nyomanavowstest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inyomanw.nyomanavowstest.data.room.table.CategoryTable

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categoryTable: List<CategoryTable>)

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<CategoryTable>
}