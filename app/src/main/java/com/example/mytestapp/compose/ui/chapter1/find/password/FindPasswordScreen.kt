package com.example.mytestapp.compose.ui.chapter1.find.password

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Background
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun FindPasswordScreen() {
    var isMobileSelect by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "비밀번호 찾기",
            onBackClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "암호를 재설정하는 데 필요한 코드번호를 받으실 방법을 선택해 주세요",
            style = TextStyle(
                fontFamily = pretendard,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Chapter1TextGray,
                lineHeight = 24.sp,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(50.dp))

        FindPasswordItem(
            iconRes = R.drawable.ic_chat,
            title = "휴대폰 번호",
            content = "010-1234-5678",
            isSelect = isMobileSelect,
            onClick = {
                isMobileSelect = true
            }
        )

        FindPasswordItem(
            iconRes = R.drawable.ic_message,
            title = "이메일",
            content = "kmj94***@naver.com",
            isSelect = isMobileSelect.not(),
            modifier = Modifier.padding(top = 12.dp),
            onClick = {
                isMobileSelect = false
            }
        )

        Spacer(Modifier.weight(1f))

        CommonRoundedButton(
            text = "코드 보내기",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) { }
    }
}

@Composable
fun FindPasswordItem(
    @DrawableRes
    iconRes: Int,
    title: String,
    content: String,
    isSelect: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = if (isSelect) 1.dp else (-1).dp,
                color = Chapter1MainColor,
                shape = RoundedCornerShape(20)
            )
            .background(
                color = if (isSelect) Color.Transparent else Chapter1Black,
                shape = RoundedCornerShape(20)
            )
            .padding(vertical = 28.dp, horizontal = 24.dp)
            .nonRippleClickable(onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = if (isSelect) Chapter1MainColor.copy(alpha = 0.1f) else Chapter1Background,
                    shape = CircleShape
                )
                .size(56.dp)
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                colorFilter = if (isSelect) ColorFilter.tint(Chapter1MainColor) else null,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                title,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Chapter1TextGray,
                    lineHeight = 20.sp,
                    letterSpacing = -(0.025).sp
                )
            )

            Text(
                content,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = if (isSelect) Color.White else Chapter1TextGray,
                    lineHeight = 20.sp,
                    letterSpacing = -(0.025).sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Preview
@Composable
fun FindPasswordScreenPreview() {
    PreviewContainer {
        FindPasswordScreen()
    }
}