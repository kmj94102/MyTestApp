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
fun RegistrationOfBiometricsScreen() {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Chapter1GNB(
            title = "지문인증 등록",
            onBackClick = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(120.dp))

        RegistrationOfBiometricsItem()
        Spacer(Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            CommonRoundedButton(
                text = "다음에",
                backgroundColor = Chapter1Black,
                textColor = Chapter1TextGray,
                modifier = Modifier.weight(1f)
            ) { }

            CommonRoundedButton(
                text = "사용 동의",
                modifier = Modifier.weight(1f)
            ) { }
        }
    }
}

@Composable
fun RegistrationOfBiometricsItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "빠른 이용을 위해\n생체인증을 설정하세요",
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
            "빠른 이용을 위해 생체인증을 설정하세요",
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
            painter = painterResource(R.drawable.img_vector),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(140.dp, 158.dp)
        )
    }
}

@Preview
@Composable
fun RegistrationOfBiometricsPreview() {
    PreviewContainer {
        RegistrationOfBiometricsScreen()
    }
}