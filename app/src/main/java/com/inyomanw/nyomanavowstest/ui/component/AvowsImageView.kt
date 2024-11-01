package com.inyomanw.nyomanavowstest.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun AvowsImageView(url: String, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(data = url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ClickableImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var onCLickState by remember {
        mutableStateOf(false)
    }


    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .size(25.dp)
            .clickable {
                coroutineScope.launch { onCLickState = !onCLickState }
            }
    )

    if (onCLickState){
        onClick()
    }
}