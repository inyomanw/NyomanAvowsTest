package com.inyomanw.nyomanavowstest.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    val listProduct : List<Product>
)


data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("rating")
    val rating: RatingProduct
)

data class RatingProduct(
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("count")
    val count: Int
)
