package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier, @DrawableRes iconRes: Int, text: String, onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .nonRippleClickable(onClick)
            .background(color = Color(0xFF1F222A), shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = Color(0xFF35383F), shape = RoundedCornerShape(16.dp))
            .padding(18.dp)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))

        Text(
            text,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                letterSpacing = (-0.025).em,
                color = Color.White
            )
        )
    }
}

@Composable
fun SmallSocialLoginButton(
    modifier: Modifier = Modifier, @DrawableRes iconRes: Int, isFilter: Boolean = false, onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .nonRippleClickable(onClick)
            .background(color = Color(0xFF1F222A), shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = Color(0xFF35383F), shape = RoundedCornerShape(16.dp))
            .width(80.dp)
            .padding(18.dp)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            colorFilter = if (isFilter) ColorFilter.tint(Color(0xFF676E81), BlendMode.SrcIn) else null,
            modifier = Modifier.size(24.dp)
        )
    }
}