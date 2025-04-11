package com.example.mytestapp.compose.ui.chapter1.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.SocialLoginButton
import com.example.mytestapp.compose.unit.nonRippleClickable
import com.example.mytestapp.compose.navigation.Chapter1Screen

@Composable
fun LoginScreen(
    navHostController: NavHostController? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "",
            onBackClick = {
                navHostController?.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.img_login),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 34.dp)
                        .size(200.dp)
                )
            }

            item {
                LoginButtons()
            }

            item {
                CommonRoundedButton(
                    text = "로그인",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp)
                ) {
                    navHostController?.navigate(Chapter1Screen.EmailLogin)
                }
            }
        } // LazyColumn

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .nonRippleClickable {
                    navHostController?.navigate(Chapter1Screen.SingUp)
                }
        ) {
            Text(
                "아직 회원이 아니신가요?",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Chapter1TextGray,
                    lineHeight = 22.sp,
                    letterSpacing = -(0.025).sp
                )
            )

            Text(
                "회원 가입",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textDecoration = TextDecoration.Underline,
                    color = Chapter1MainColor,
                    lineHeight = 22.sp,
                    letterSpacing = -(0.025).sp
                ),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Composable
fun LoginButtons() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        SocialLoginButton(
            iconRes = R.drawable.ic_kakao_logo,
            text = "카카오 로그인",
            modifier = Modifier.fillMaxWidth()
        ) { }

        SocialLoginButton(
            iconRes = R.drawable.ic_google_logo,
            text = "구글로 로그인",
            modifier = Modifier.fillMaxWidth()
        ) { }

        SocialLoginButton(
            iconRes = R.drawable.ic_apple_logo,
            text = "애플로 로그인",
            modifier = Modifier.fillMaxWidth()
        ) { }
    }
}

@Preview
@Composable
fun Chapter1LoginPreview() {
    PreviewContainer {
        LoginScreen()
    }
}