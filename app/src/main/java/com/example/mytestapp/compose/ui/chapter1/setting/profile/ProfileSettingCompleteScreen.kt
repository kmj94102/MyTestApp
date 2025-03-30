package com.example.mytestapp.compose.ui.chapter1.setting.profile

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
fun ProfileSettingCompleteScreen() {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Chapter1GNB(
            title = "설정 완료",
            onBackClick = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(127.dp))

        ProfileSettingCompleteItem()
        Spacer(Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp))  {
            CommonRoundedButton(
                text = "다음에",
                backgroundColor = Chapter1Black,
                textColor = Chapter1TextGray,
                modifier = Modifier.weight(1f)
            ) { }

            CommonRoundedButton(
                text = "투자 시작",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) { }
        }
    }
}

@Composable
fun ProfileSettingCompleteItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_complete),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(226.dp, 170.dp)
        )
        Spacer(Modifier.height(32.dp))

        Text(
            "설정이 완료되었습니다",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Color.White,
                letterSpacing = (-0.025).em,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(16.dp))

        Text(
            "___님은 부자가 되실 겁니다.\n현명한 투자로 미래를 보장 받으세요",
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
    }
}

@Preview
@Composable
fun PasswordCompletePreview() {
    PreviewContainer {
        ProfileSettingCompleteScreen()
    }
}