package com.example.mytestapp.compose.ui.chapter1.new_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton

@Composable
fun PasswordCompleteScreen() {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Spacer(Modifier.height(220.dp))

        PasswordCompleteItem()
        Spacer(Modifier.weight(1f))

        CommonRoundedButton(
            text = "로그인",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) { }
    }
}

@Composable
fun PasswordCompleteItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "비밀번호 재설정 완료",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = Color.White,
                letterSpacing = (-0.025).em,
                lineHeight = 38.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(16.dp))

        Text(
            "새로운 비밀전호로 재설정 되었습니다.\n신규 비밀번호를 입력하셔서 로그인을 진행하세요",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Chapter1TextGray,
                letterSpacing = (-0.025).em,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(48.dp))

        Image(
            painter = painterResource(R.drawable.img_sheild),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(140.dp, 158.dp)
        )
    }
}

@Preview
@Composable
fun PasswordCompletePreview() {
    PreviewContainer {
        PasswordCompleteScreen()
    }
}