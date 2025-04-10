package com.example.mytestapp.compose.data.chapter1

import androidx.annotation.DrawableRes
import com.example.mytestapp.R

data class Intro(
    val title: String,
    val message: String,
    @DrawableRes val imageRes: Int
)

fun fetchIntroList() = listOf(
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