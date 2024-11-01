package com.inyomanw.nyomanavowstest.data.request

import com.google.gson.annotations.SerializedName
import com.inyomanw.nyomanavowstest.data.response.CartProduct

data class CartRequest(
    @SerializedName("UserId")
    val userId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("products")
    val products: List<CartProduct>
)
