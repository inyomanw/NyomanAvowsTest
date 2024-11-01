package com.inyomanw.nyomanavowstest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inyomanw.nyomanavowstest.data.room.dao.CartDao
import com.inyomanw.nyomanavowstest.data.room.dao.CategoryDao
import com.inyomanw.nyomanavowstest.data.room.dao.ProductDao
import com.inyomanw.nyomanavowstest.data.room.dao.UserDao
import com.inyomanw.nyomanavowstest.data.room.table.AddressTable
//import com.inyomanw.nyomanavowstest.data.room.table.CartProductTable
//import com.inyomanw.nyomanavowstest.data.room.table.CartTable
import com.inyomanw.nyomanavowstest.data.room.table.CategoryTable
import com.inyomanw.nyomanavowstest.data.room.table.ProductTable
import com.inyomanw.nyomanavowstest.data.room.table.RatingProductTable
import com.inyomanw.nyomanavowstest.data.room.table.UserTable

@Database(
    entities = [
        UserTable::class, AddressTable::class,CategoryTable::class,ProductTable::class,
        RatingProductTable::class,
//        CartTable::class, CartProductTable::class
               ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun categoryDao(): CategoryDao
}
