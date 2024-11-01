package com.inyomanw.nyomanavowstest.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AvowsProgressbar(isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            initialAlpha = 0.4f
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 250)
        ), modifier = Modifier.wrapContentSize()

    ) {
        CircularProgressIndicator(strokeWidth = 5.dp)
    }
}