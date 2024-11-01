package com.inyomanw.nyomanavowstest.data.response

class TestUsers : ArrayList<TestUsersItem>()

data class TestUsersItem(
    val __v: Int,
    val address: Address,
    val email: String,
    val id: Int,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)