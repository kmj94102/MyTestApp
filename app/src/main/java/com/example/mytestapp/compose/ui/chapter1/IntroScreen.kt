package com.example.mytestapp.compose.ui.chapter1

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.data.chapter1.Intro
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.navigation.Chapter1Screen

@Composable
fun IntroScreen(
    controller: NavHostController?= null,
    viewModel: IntroViewModel = hiltViewModel()
) {
    val state = rememberPagerState(initialPage = 0, pageCount = { viewModel.list.size })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state, modifier = Modifier.weight(1f)) {
            IntroItem(viewModel.list[it])
        }

        Indicator(state.currentPage, viewModel.list.size)

        CommonRoundedButton(
            text = "시작하기",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp)
        ) {
            controller?.navigate(Chapter1Screen.Login)
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