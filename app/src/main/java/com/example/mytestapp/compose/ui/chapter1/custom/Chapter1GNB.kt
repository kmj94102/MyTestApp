package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.R
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun Chapter1GNB(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.height(56.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.nonRippleClickable(onBackClick)
                .align(Alignment.CenterStart)
                .size(28.dp)
        )

        Text(
            title,
            style = TextStyle(
                fontFamily = pretendard,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                lineHeight = 34.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}