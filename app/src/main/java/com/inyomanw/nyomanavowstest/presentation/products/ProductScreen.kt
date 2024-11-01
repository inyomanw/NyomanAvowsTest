package com.inyomanw.nyomanavowstest.presentation.products

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.inyomanw.nyomanavowstest.R
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import com.inyomanw.nyomanavowstest.ui.component.AvowsErrorMessage
import com.inyomanw.nyomanavowstest.ui.component.AvowsImageView
import com.inyomanw.nyomanavowstest.ui.component.AvowsProgressbar
import com.inyomanw.nyomanavowstest.ui.component.AvowsTextView
import com.inyomanw.nyomanavowstest.ui.component.ClickableImage
import com.inyomanw.nyomanavowstest.ui.component.CustomSpinner

@Composable
fun ProductScreen(userName: String, viewModel: ProductsViewModel, navHostController: NavHostController) {
    LaunchedEffect(key1 = "") {
        viewModel.getProducts()
        viewModel.getCategories()
        viewModel.getUserByUsername(userName)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            val isVisible: Boolean by viewModel.isLoading.collectAsState()
            val errorMessage: String by viewModel.errorMessage.collectAsState()
            val listProduct: List<Product> by viewModel.productResponse.collectAsState()
            val categories: List<String> by viewModel.categories.collectAsState()

            val userWithAddress: UserWithAddress? by viewModel.userWithAddress.collectAsState()

            val test = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
//            Log.e("loge", "getCategories productScreen : ${categories.size}")
//            Log.e("loge", "getCategories productScreen 0 : ${categories[1]}")

            Log.e("loge","product Screnn user : ${userWithAddress?.user?.email}")
            var selectedOption by remember { mutableStateOf("") }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ClickableImage(painter = painterResource(id = R.drawable.category)) {

                }

//                CustomSpinner(
//                    options = test,
//                    selectedOption = selectedOption,
//                    onOptionSelected = { selected ->
//                        selectedOption = selected
//                        Log.e("loge", "selected spinner : $selected")
//                    },
//                    label = "Select category"
//                )
                Spacer(modifier = Modifier.width(16.dp))

                ClickableImage(painter = painterResource(id = R.drawable.user)) {
                    userWithAddress?.let { BottomSheetProfile(it) }
                }
                Spacer(modifier = Modifier.width(16.dp))

                ClickableImage(painter = painterResource(id = R.drawable.cart)) {
                   LaunchedEffect(key1 = "") {
                       navHostController.navigate("cart")
                   }
                }

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
            if (listProduct.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AvowsErrorMessage(errorMsg = "Not Found")
                }
                return@Surface
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
            ) {
                items(listProduct.size ?: 0) {
                    val data = listProduct[it]
                    AvowsListProduct(
                        product = data,
                        onItemClick = {
                            navHostController.navigate("detailProduct/${data.id}")
                        })
                }
            }

        }
    }
}

@Composable
fun AvowsListProduct(onItemClick: (Product) -> Unit, product: Product) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 16.dp)
            .clickable { onItemClick(product) }
    ) {
        AvowsImageView(
            url = product.image.toString(),
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(RectangleShape)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            AvowsTextView(
                text = product.title.toString(),
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            AvowsTextView(
                text = "$${product.price.toString()}",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )
        }
    }
}


