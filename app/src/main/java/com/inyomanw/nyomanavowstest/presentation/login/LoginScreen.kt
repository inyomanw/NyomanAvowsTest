package com.inyomanw.nyomanavowstest.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inyomanw.nyomanavowstest.data.request.LoginRequest
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import com.inyomanw.nyomanavowstest.ui.component.AvowsErrorMessage
import com.inyomanw.nyomanavowstest.ui.component.AvowsProgressbar
import com.inyomanw.nyomanavowstest.ui.component.AvowsTextField


@Composable
fun LoginScreen(viewModel: LoginViewModel, navHostController: NavHostController) {
    val usernameState = remember { mutableStateOf(TextFieldValue("mor_2314")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("83r5^_")) }

    val user = remember { mutableStateOf<UserWithAddress?>(null) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            val isVisible: Boolean by viewModel.isLoading.collectAsState()
            val errorMessage: String by viewModel.errorMessage.collectAsState()
            val isSuccess: Boolean by viewModel.isSuccess.collectAsState()


            if (isSuccess) {
                LaunchedEffect(key1 = isSuccess) {
                    navHostController.navigate("product/${usernameState.value.text}") {
                        popUpTo("currentRoute") { inclusive = true }
                    }

                }

            }
            LaunchedEffect(key1 = errorMessage) {
                if (errorMessage.isNotEmpty())
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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

            AvowsTextField(
                value = usernameState,
                label = "Enter Username",
                onValueChange = { },
                imeAction = ImeAction.Next,
                onImeAction = { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AvowsTextField(
                value = passwordState,
                label = "Enter password",
                onValueChange = { },
                isPassword = true,
                imeAction = ImeAction.Done,
                onImeAction = { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.login(
                        LoginRequest(
                            usernameState.value.text, passwordState.value.text
                        )
                    )
                    viewModel.getUser(usernameState.value.text)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }

}
