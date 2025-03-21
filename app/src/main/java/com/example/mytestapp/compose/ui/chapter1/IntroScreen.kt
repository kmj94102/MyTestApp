package com.example.mytestapp.compose.ui.chapter1

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton

data class Intro(
    val title: String,
    val message: String,
    @DrawableRes val imageRes: Int
)

@Composable
fun IntroScreen() {
    val list = listOf(
        Intro(
            title = "채굴 어렵지 않아요",
            message = "채굴이라 하면 너무 어렵 생각하는데\n누구나 쉽게 채굴이 가능합니다.",
            imageRes = R.drawable.img_intro1
        ),
        Intro(
            title = "투자로 인생을 바꾸세요",
            message = "한 번뿐인 인생의 가상화폐 투자로\n또 다른 나를 찾아 세상을 넓혀보세요",
            imageRes = R.drawable.img_intro2
        ),
        Intro(
            title = "내 자산을 안전하게",
            message = "남들에게 보이지 않고 도난당할 일 없는\n안전하게 자산을 지키세요",
            imageRes = R.drawable.img_intro3
        )
    )
    val state = rememberPagerState(initialPage = 0, pageCount = { list.size })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state, modifier = Modifier.weight(1f)) {
            IntroItem(list[it])
        }

        Indicator(state.currentPage, list.size)

        CommonRoundedButton(
            text = "시작하기",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp)
        ) {

        }
    }
}

@Composable
fun IntroItem(intro: Intro) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = intro.imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Text(
            intro.title,
            style = TextStyle(
                color = Color.White,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 44.sp,
                fontSize = 32.sp,
                letterSpacing = -(0.025).em,
                textAlign = TextAlign.Center
            ), modifier = Modifier.padding(top = 32.dp, bottom = 12.dp)
        )
        Text(
            intro.message,
            style = TextStyle(
                color = Color.White,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                fontSize = 14.sp,
                letterSpacing = -(0.025).em,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun Indicator(index: Int, size: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 32.dp)
    ) {
        (0..<size).forEach {
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .height(8.dp)
                    .width(if (it == index) 32.dp else 8.dp)
                    .background(
                        color = if (it == index) {
                            Chapter1MainColor
                        } else {
                            Color(0xFF626877)
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    PreviewContainer { IntroScreen() }
}