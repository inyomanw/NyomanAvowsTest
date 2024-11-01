package com.inyomanw.nyomanavowstest.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun AvowsTextView(
    text: String,
    modifier: Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.Black
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(color = textColor, fontSize = fontSize, fontWeight = fontWeight)
        )
}