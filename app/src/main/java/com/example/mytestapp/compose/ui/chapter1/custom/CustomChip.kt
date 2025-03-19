package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.pretendard

@Composable
fun CustomChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    count: Int? = null
) {
    Box {
        Row(
            modifier = modifier
                .padding(top = 4.dp, end = 4.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    if (isSelect) {
                        Chapter1MainColor
                    } else {
                        Color.Transparent
                    }
                )
                .border(
                    width = if (isSelect) {
                        0.dp
                    } else {
                        1.dp
                    },
                    color = Chapter1MainColor.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(vertical = 14.dp, horizontal = 15.dp)
        ) {
            Text(
                text,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.025).em,
                    color = if (isSelect) {
                        Color.White
                    } else {
                        Color(0xFF2C8DFF)
                    }
                )
            )
        }

        if(isSelect) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .size(20.dp)
                    .background(Color(0xFF00C436))
            ) {
                Text(
                    count.toString(),
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        letterSpacing = (-0.025).em,
                        color = Color.White
                    )
                )
            }
        }
    }
}