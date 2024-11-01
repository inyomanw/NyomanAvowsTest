package com.inyomanw.nyomanavowstest.data.room.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

data class UserWithAddress(
    @Embedded val user: UserTable,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "idAddress"
    )
    val address: AddressTable
)

@Entity(
    tableName = "user",
)
data class UserTable(
    @PrimaryKey @ColumnInfo("idUser") val id: Int,
    val username:String,
    val firstName: String,
    val lastName: String,
    val phone: Int,
    val email: String,
    @ColumnInfo("addressId") val addressId: String
)

@Entity(tableName = "address")
data class AddressTable(
    @PrimaryKey @ColumnInfo("idAddress") val id: String,
    val city: String,
    val number: Int,
    val street: String,
    val zipcode: String
)

