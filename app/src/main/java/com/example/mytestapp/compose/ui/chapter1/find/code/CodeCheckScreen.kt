package com.example.mytestapp.compose.ui.chapter1.find.code

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CodeTextFiled
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.unit.nonRippleClickable
import com.example.mytestapp.util.toast

@Composable
fun CodeCheckScreen(
    navHostController: NavHostController? = null,
    viewModel: CodeCheckViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "코드 입력",
            onBackClick = {
                navHostController?.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "복구 코드가 귀하에게 전송되었습니다.\n전달 받은 코드를 2분 안에 입력 하셔야 합니다.",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Chapter1TextGray,
                lineHeight = 22.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            buildAnnotatedString {
                append("010-1234-5678 ")
                withStyle(SpanStyle(color = Chapter1TextGray)) {
                    append("코드를 보냈습니다.")
                }
            },
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 20.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            "코드 다시보내기",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Chapter1MainColor,
                lineHeight = 20.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.padding(top = 12.dp)
                .nonRippleClickable(viewModel::restartCountdown)
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            item {
                CodeTextFiled(
                    text = viewModel.state.value.code,
                    onChange = viewModel::updateCode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            item {
                Text(
                    buildAnnotatedString {
                        append("코드 입력까지 ")
                        withStyle(SpanStyle(color = Chapter1MainColor)) {
                            append("${viewModel.state.value.secondsLeft}초")
                        }
                        append(" 남았습니다")
                    },
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Chapter1TextGray,
                        lineHeight = 20.sp,
                        letterSpacing = -(0.025).em
                    ),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        CommonRoundedButton(
            text = "입력완료",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = viewModel::checkCode
        )
    }

    LaunchedEffect(Unit) {
        viewModel.startCountdown()
    }

    LaunchedEffect(uiState) {
        when(uiState) {
            is CodeCheckUiState.Success -> {
                context.toast("코드 확인 완료")
                navHostController?.navigate(Chapter1Screen.EmailLogin) {
                    popUpTo(Chapter1Screen.EmailLogin) {
                        inclusive = true
                    }
                }
            }
            is CodeCheckUiState.Error -> {
                context.toast(
                    (uiState as CodeCheckUiState.Error).message
                )
            }
            else -> {}
        }
    }
}

@Preview
@Composable
fun CodeCheckScreenPreview() {
    PreviewContainer {
        CodeCheckScreen()
    }
}