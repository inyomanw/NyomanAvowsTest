package com.inyomanw.nyomanavowstest.presentation.products

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inyomanw.nyomanavowstest.data.request.CartRequest
import com.inyomanw.nyomanavowstest.data.response.CartProduct
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.room.table.ProductWithRating
import com.inyomanw.nyomanavowstest.ui.component.AvowsErrorMessage
import com.inyomanw.nyomanavowstest.ui.component.AvowsImageView
import com.inyomanw.nyomanavowstest.ui.component.AvowsProgressbar
import com.inyomanw.nyomanavowstest.ui.component.AvowsTextView
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailProductScreen(productId: Int, viewModel: ProductsViewModel) {

   LaunchedEffect(key1 = "") {
       viewModel.getDetailProduct(productId)
   }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            val product: Product? by viewModel.detailProduct.collectAsState()
            val isVisible: Boolean by viewModel.isLoading.collectAsState()
            val errorMessage: String by viewModel.errorMessage.collectAsState()
            val isSuccess: Boolean by viewModel.isSuccess.collectAsState()
            val list : List<ProductWithRating> by viewModel.list.collectAsState(initial = emptyList())

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


          LaunchedEffect(key1 = isSuccess) {
              if (isSuccess) {
                  Toast.makeText(context, "Succeed add Cart! ${list.size}", Toast.LENGTH_SHORT).show()
              }
          }

            LaunchedEffect(key1 = errorMessage) {
                if (errorMessage.isNotEmpty())
                    Toast.makeText(context, "Fail to  add Cart!", Toast.LENGTH_SHORT).show()
            }

            AvowsImageView(
                url = product?.image.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RectangleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))
            AvowsTextView(
                text = product?.title.toString(),
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            AvowsTextView(
                text = product?.description.toString(),
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            AvowsTextView(
                text = "$${product?.price.toString()}",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            AvowsTextView(
                text = "category : ${product?.category.toString()}",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            AvowsTextView(
                text = "rating : ${product?.rating?.rate.toString()}",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                          viewModel.addCart(cartRequest = CartRequest(
                              userId = 2,
                              date = getDateNow(),
                              listOf(CartProduct(productId = product?.id ?: 0, quantity = 1))
                          ))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add to Cart")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDateNow(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return current.format(formatter)
}