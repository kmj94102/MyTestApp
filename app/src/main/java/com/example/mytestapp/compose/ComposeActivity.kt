package com.example.mytestapp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.R
import com.example.mytestapp.compose.theme.Chapter1Background
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.MyTestAppTheme
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.ui.chapter1.custom.CustomChip
import com.example.mytestapp.compose.ui.chapter1.custom.RoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.SmallSocialLoginButton
import com.example.mytestapp.compose.ui.chapter1.custom.SocialLoginButton
import com.example.mytestapp.navigation.ComposeNavigationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .background(Chapter1Background)
                            .padding(innerPadding)
                    ) {
                        val navHostController = rememberNavController()
                        ComposeNavigationHost(navHostController)
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewContainer(item: @Composable () -> Unit) {
    MyTestAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Chapter1Background)
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PreviewContainer {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            RoundedButton(
                text = {
                    Text(
                        "테스트",
                        style = TextStyle(
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                },
                backgroundColor = Chapter1MainColor,
                modifier = Modifier.fillMaxWidth()
            )

            SocialLoginButton(
                iconRes = R.drawable.ic_kakao_logo,
                text = "카카오로 로그인",
                modifier = Modifier.fillMaxWidth()
            ) { }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SmallSocialLoginButton(
                    iconRes = R.drawable.ic_kakao_logo
                ) { }

                SmallSocialLoginButton(
                    iconRes = R.drawable.ic_google_logo,
                    isFilter = true
                ) { }

                SmallSocialLoginButton(
                    iconRes = R.drawable.ic_apple_logo
                ) { }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                CustomChip(
                    text = "가상화폐",
                    isSelect = true,
                    count = 1
                )

                CustomChip(
                    text = "가상화폐",
                    isSelect = false,
                )

                CustomChip(
                    text = "금리",
                    isSelect = true,
                    count = 2
                )

                CustomChip(
                    text = "금리",
                    isSelect = false,
                )
            }

            var text1 by remember { mutableStateOf("") }
            var text2 by remember { mutableStateOf("") }
            CommonTextField(
                label = "test",
                value = text1,
                hint = "test hint",
                modifier = Modifier.fillMaxWidth(),
                onTextChange = { text1 = it },
                trailingIcon = {
                    if (it) {
                        Image(painter = painterResource(R.drawable.ic_show), contentDescription = null)
                    }else {
                        Image(painter = painterResource(R.drawable.ic_hide), contentDescription = null)
                    }
                }
            )
            CommonTextField(
                value = text2,
                modifier = Modifier.fillMaxWidth(),
                onTextChange = { text2 = it },
            )
        }
    }
}