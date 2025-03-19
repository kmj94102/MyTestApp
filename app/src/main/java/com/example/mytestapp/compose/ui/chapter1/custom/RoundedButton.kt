package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    backgroundColor: Color,
    radius: Dp = 16.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(radius)
            )
            .padding(vertical = 13.dp)
    ) {
        text()
    }
}