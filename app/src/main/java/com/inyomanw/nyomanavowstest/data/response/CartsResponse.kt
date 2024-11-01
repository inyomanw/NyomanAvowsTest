package com.inyomanw.nyomanavowstest.data.response

import com.google.gson.annotations.SerializedName

data class CartsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("UserId")
    val userId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("products")
    val products: List<CartProduct>
)

data class CartProduct(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)