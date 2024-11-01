package com.inyomanw.nyomanavowstest.presentation.cart

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inyomanw.nyomanavowstest.data.response.CartProduct
import com.inyomanw.nyomanavowstest.data.response.CartsResponse
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.ui.component.AvowsErrorMessage
import com.inyomanw.nyomanavowstest.ui.component.AvowsProgressbar
import com.inyomanw.nyomanavowstest.ui.component.AvowsTextView


@Composable
fun CartScreen(viewModel: CartViewModel) {



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
        ) {

            val listChat : List<ProductWithRating> by viewModel.listCart.collectAsState(initial = emptyList())
            val carts: List<CartsResponse> by viewModel.carts.collectAsState()
            val isVisible: Boolean by viewModel.isLoading.collectAsState()
            val errorMessage: String by viewModel.errorMessage.collectAsState()
            val isSuccess: Boolean by viewModel.isSuccess.collectAsState()


            LaunchedEffect(key1 = "") {
                viewModel.getUserCarts(2)
            }

            if (isVisible) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AvowsProgressbar(isVisible = true)
                }
                return@Surface
            }

            if (errorMessage != "") {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AvowsErrorMessage(errorMsg = errorMessage)
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(listChat.size ?: 0) {
                    val data = listChat[it]
                    ListProductCart(data)
                }
            }
        }
    }
}

@Composable
fun ListCart(cartsResponse: CartsResponse,list: List<ProductWithRating>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        AvowsTextView(
            text = "id cart : ${cartsResponse.id}",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        AvowsTextView(
            text = "date : ${cartsResponse.date}",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 14.sp
        )

    }
}


@Composable
fun ListProductCart(product: ProductWithRating) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        AvowsTextView(
            text = "productId : ${product.product.id}",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}