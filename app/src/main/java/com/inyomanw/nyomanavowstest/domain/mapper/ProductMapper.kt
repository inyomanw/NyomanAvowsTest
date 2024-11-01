package com.inyomanw.nyomanavowstest.domain.mapper

import android.adservices.adid.AdId
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.response.RatingProduct
import com.inyomanw.nyomanavowstest.data.room.table.ProductTable
import com.inyomanw.nyomanavowstest.data.room.table.RatingProductTable

fun Product.toProductTable(): ProductTable {
    return ProductTable(
        id = this.id,
        title = this.title,
        price = this.price,
        category = this.category,
        description = this.description,
        image = this.image,
        ratingId = rating.count.toString()+rating.rate.toString()+id.toString()
    )
}

fun RatingProduct.toRatingProductTable(productId: Int) = RatingProductTable(count.toString()+rate.toString()+productId.toString(), rate, count)
