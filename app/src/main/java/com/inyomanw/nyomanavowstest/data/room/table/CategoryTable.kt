package com.inyomanw.nyomanavowstest.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category", indices = [Index(value = ["category"], unique = true)])
data class CategoryTable(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("idCategory") val id: Int = 0,
    val category: String
)
