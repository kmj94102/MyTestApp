package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun CodeTextFiled(
    text: String,
    onChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .nonRippleClickable {
                focusRequester.requestFocus()
            }
    ) {
        CodeTextFieldItem("${text.firstOrNull() ?: ""}")
        CodeTextFieldItem("${text.getOrNull(1) ?: ""}")
        CodeTextFieldItem("${text.getOrNull(2) ?: ""}")
        CodeTextFieldItem("${text.getOrNull(3) ?: ""}")
    }
    BasicTextField(
        value = text,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .alpha(0f)
    )
}

@Composable
fun CodeTextFieldItem(
    value: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(68.dp)
            .border(
                if (value.isNotEmpty()) {
                    1.dp
                } else {
                    -(1).dp
                },
                color = Chapter1MainColor,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = if (value.isNotEmpty()) {
                    Chapter1MainColor.copy(alpha = 0.1f)
                } else {
                    Chapter1Black
                },
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            value,
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                letterSpacing = -(0.025).em,
            )
        )
    }
}

@Preview
@Composable
fun CodeTextFiledPreview() {
    var text by remember { mutableStateOf("") }
    PreviewContainer {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            CodeTextFiled(
                text = text,
                onChange = {
                    val value = it.trim().filter { value -> value.isDigit() }
                    if (value.trim().length <= 4) {
                        text = value
                    }
                }
            )
        }
    }
}