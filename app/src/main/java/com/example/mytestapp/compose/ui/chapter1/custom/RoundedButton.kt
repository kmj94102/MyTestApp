package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    backgroundColor: Color,
    radius: Dp = 16.dp,
    onClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(radius)
            )
            .padding(vertical = 13.dp)
            .nonRippleClickable(onClick)
    ) {
        text()
    }
}

@Composable
fun CommonRoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    backgroundColor: Color = Chapter1MainColor,
    radius: Dp = 16.dp,
    onClick: () -> Unit
) {
    RoundedButton(
        modifier = modifier,
        text = {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = textColor,
                    lineHeight = 26.sp,
                    letterSpacing = -(0.025).em
                )
            )
        },
        backgroundColor = backgroundColor,
        radius = radius,
        onClick = onClick
    )
}