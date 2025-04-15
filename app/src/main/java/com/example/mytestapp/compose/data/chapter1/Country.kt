package com.example.mytestapp.compose.data.chapter1

import androidx.annotation.DrawableRes
import com.example.mytestapp.R

enum class Country(val koreaName: String, @DrawableRes val imageRes: Int) {
    KOREA("한국", R.drawable.ic_korea),
    USA("미국", R.drawable.ic_usa),
    INDIA("인도네시아", R.drawable.ic_india),
    ARGENTINA("아르헨티나", R.drawable.img_argentina),
    ITALY("이탈리아", R.drawable.ic_italy),
    CANADA("캐나다", R.drawable.ic_canada);

    companion object {
        fun getCountryImage(koreaName: String): Int {
            return entries.find { it.koreaName == koreaName }?.imageRes ?: R.drawable.ic_korea
        }
    }
}