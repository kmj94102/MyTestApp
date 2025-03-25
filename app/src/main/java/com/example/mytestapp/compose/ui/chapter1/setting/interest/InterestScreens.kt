package com.example.mytestapp.compose.ui.chapter1.setting.interest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CustomChip
import com.example.mytestapp.compose.unit.nonRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestScreen() {
    val list by remember { mutableStateOf(listOf(
        "가상화폐", "금리", "부동산", "투자", "10억", "부자 되는 방법", "제테크", "갭투자",
        "타이밍", "비트코인", "리플", "이더리움", "주식투자", "계좌개설", "코인", "도지코인",
        "안전거래", "대출", "ETF", "레버리지", "미국 나스닥", "펀드", "S&P500", "트론",
        "에이다", "스텔라루멘", "이오스"
    )) }
    var selectList by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Chapter1GNB(
            title = "나의 관심사 선택",
            modifier = Modifier.fillMaxWidth(),
            onBackClick = {}
        )

        Text(
            "고객님이 평소 좋아하는 관심사 키워드 7개를 선택하세요\n선택하신 키워드는 언제든 바꿀 수 있습니다.",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Chapter1TextGray,
                lineHeight = 20.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        FlowRow (
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f).fillMaxWidth().padding(top = 30.dp)
        ) {
            list.forEach {
                CustomChip(
                    text = it,
                    isSelect = selectList.contains(it),
                    count = selectList.indexOf(it) + 1,
                    modifier = Modifier.nonRippleClickable {
                        selectList = if (selectList.contains(it)) {
                            selectList.filter { value -> value != it }
                        } else {
                            if(selectList.size < 7) {
                                selectList + it
                            } else {
                                selectList
                            }
                        }
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            CommonRoundedButton(
                text = "다음에",
                textColor = Chapter1TextGray,
                backgroundColor = Chapter1Black,
                modifier = Modifier.weight(1f)
            ) { }
            Spacer(modifier = Modifier.width(8.dp))

            CommonRoundedButton(text = "선택완료", modifier = Modifier.weight(1f)) { }
        }
    }
}

@Preview
@Composable
fun InterestScreenPreview() {
    PreviewContainer {
        InterestScreen()
    }
}
