package com.inyomanw.nyomanavowstest.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvowsErrorMessage(errorMsg: String) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        AvowsTextView(
            text = errorMsg,
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textColor = Color.Red
        )
    }
}