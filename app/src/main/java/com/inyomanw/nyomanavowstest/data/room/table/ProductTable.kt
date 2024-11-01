package com.inyomanw.nyomanavowstest.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

import androidx.room.*

@Entity(
    tableName = "product"
)
data class ProductTable(
    @PrimaryKey @ColumnInfo(name = "idProduct") val id: Int,
    val title: String?,
    val price: Double?,
    val category: String?,
    val description: String?,
    val image: String?,
    @ColumnInfo(name = "ratingId") val ratingId: String?
)

@Entity(tableName = "ratingProduct")
data class RatingProductTable(
    @PrimaryKey @ColumnInfo(name = "idRating") val id:String,
    val rate: Double?,
    val count: Int
)

data class ProductWithRating(
    @Embedded val product: ProductTable,
    @Relation(
        parentColumn = "ratingId",
        entityColumn = "idRating"
    )
    val rating: RatingProductTable?
)
