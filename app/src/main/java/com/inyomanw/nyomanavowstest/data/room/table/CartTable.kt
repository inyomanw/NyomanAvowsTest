package com.inyomanw.nyomanavowstest.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

//@Entity(tableName = "cart")
//data class CartTable(
//    @PrimaryKey @ColumnInfo(name = "cartId") val id: Int,
//    @ColumnInfo(name = "userId") val userId: Int,
//    @ColumnInfo(name = "date") val date: String
//)
//
//@Entity(
//    tableName = "cartProduct"
//)
//data class CartProductTable(
//    @PrimaryKey @ColumnInfo(name = "id") val id: String,
//    @ColumnInfo(name = "cartId") val cartId: Int,
//    @ColumnInfo(name = "productId") val productId: Int,
//    @ColumnInfo(name = "quantity") val quantity: Int
//)