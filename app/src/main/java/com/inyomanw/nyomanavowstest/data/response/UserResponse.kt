package com.inyomanw.nyomanavowstest.data.response

data class UserResponse(
    val address: Address,
    val email: String,
    val id: Int,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String,
    val __v: Int,
)

data class Address(
    val city: String,
    val geolocation: Geolocation,
    val number: Int,
    val street: String,
    val zipcode: String
)

data class Name(
    val firstname: String,
    val lastname: String
)

data class Geolocation(
    val lat: String,
    val long: String
)