package com.inyomanw.nyomanavowstest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.presentation.login.LoginViewModel
import com.inyomanw.nyomanavowstest.presentation.products.ProductsViewModel
import com.inyomanw.nyomanavowstest.ui.theme.NyomanAvowsTestTheme
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.inyomanw.nyomanavowstest.data.response.Product
import com.inyomanw.nyomanavowstest.presentation.cart.CartScreen
import com.inyomanw.nyomanavowstest.presentation.cart.CartViewModel
import com.inyomanw.nyomanavowstest.presentation.login.LoginScreen
import com.inyomanw.nyomanavowstest.presentation.products.DetailProductScreen
import com.inyomanw.nyomanavowstest.presentation.products.ProductScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val productsViewModel: ProductsViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NyomanAvowsTestTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation(loginViewModel, productsViewModel, cartViewModel)
            }
        }
    }
}

//fun navigateToDetailProduct(navController: NavHostController, product: Product) {
//    val gson = Gson()
//    val productJson = gson.toJson(product)
//    navController.navigate("detailProduct/$productJson")
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(loginViewModel: LoginViewModel, productsViewModel: ProductsViewModel, cartViewModel: CartViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(loginViewModel, navController)
        }
        composable(
            route = "product/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            ProductScreen(userName, productsViewModel, navController)
        }

        composable(
            route = "detailProduct/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            DetailProductScreen(productId, productsViewModel)
        }

        composable("cart",) {
            CartScreen(cartViewModel)
        }
    }
}