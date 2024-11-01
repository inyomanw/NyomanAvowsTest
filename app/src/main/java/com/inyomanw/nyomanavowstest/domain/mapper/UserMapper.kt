package com.inyomanw.nyomanavowstest.domain.mapper

import com.inyomanw.nyomanavowstest.data.response.Address
import com.inyomanw.nyomanavowstest.data.response.UserResponse
import com.inyomanw.nyomanavowstest.data.room.table.AddressTable
import com.inyomanw.nyomanavowstest.data.room.table.UserTable

fun UserResponse.toUserTable(): UserTable {
    return UserTable(
        id = this.id,
        firstName = this.name.firstname,
        lastName = this.name.lastname,
        phone = this.phone.toIntOrNull() ?: 0, // Convert to Int, default to 0 if conversion fails
        email = this.email,
        addressId = this.address.street + this.address.city + this.address.zipcode + this.address.number.toString(),
        username = this.username
    )
}

fun Address.toAddressTable() =
    AddressTable(id = street + city + zipcode + number.toString(), city, number, street, zipcode)