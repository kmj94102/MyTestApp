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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CustomChip
import com.example.mytestapp.compose.unit.nonRippleClickable
import com.example.mytestapp.util.toast

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestScreen(
    navHostController: NavHostController? = null,
    viewModel: InterestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        Chapter1GNB(
            title = "나의 관심사 선택",
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { navHostController?.popBackStack() }
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
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            viewModel.state.value.list.forEach {
                CustomChip(
                    text = it,
                    isSelect = viewModel.state.value.selectList.contains(it),
                    count = viewModel.state.value.selectList.indexOf(it) + 1,
                    modifier = Modifier.nonRippleClickable {
                        viewModel.updateSelectList(it)
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            CommonRoundedButton(
                text = "다음에",
                textColor = Chapter1TextGray,
                backgroundColor = Chapter1Black,
                modifier = Modifier.weight(1f),
                onClick = viewModel::onPass
            )
            Spacer(modifier = Modifier.width(8.dp))

            CommonRoundedButton(
                text = "선택완료",
                modifier = Modifier.weight(1f),
                onClick = viewModel::onSave
            )
        }
    }

    LaunchedEffect(uiState) {
        when(uiState) {
            is InterestUiState.Success -> {
                navHostController?.navigate(
                    Chapter1Screen.Profile((uiState as InterestUiState.Success).uid)
                )
            }
            is InterestUiState.Pass -> {
                navHostController?.navigate(
                    Chapter1Screen.Profile((uiState as InterestUiState.Pass).uid)
                )
            }
            is InterestUiState.Error -> {
                context.toast((uiState as InterestUiState.Error).message)
            }
            else -> {}
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
