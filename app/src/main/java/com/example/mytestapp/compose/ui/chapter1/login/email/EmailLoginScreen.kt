package com.example.mytestapp.compose.ui.chapter1.login.email

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.ui.chapter1.custom.SmallSocialLoginButton
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun EmailLoginScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "로그인",
            onBackClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            item {
                LoginForm()
            }
            item {
                SocialLoginForm()
            }
        }

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .nonRippleClickable { }
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
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column {
        CommonTextField(
            value = email,
            onTextChange = { email = it },
            label = "이메일",
            hint = "이메일을 입력해 주세요",
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_message),
                    contentDescription = null,
                    colorFilter = if (it) ColorFilter.tint(Chapter1MainColor) else null,
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.padding(top = 24.dp)
        )

        CommonTextField(
            value = password,
            onTextChange = { password = it },
            label = "비밀번호",
            hint = "비밀번호를 입력해 주세요",
            visualTransformation = if(isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    colorFilter = if (it) ColorFilter.tint(Chapter1MainColor) else null,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(
                        if (isPasswordVisible) {
                            R.drawable.ic_show
                        } else {
                            R.drawable.ic_hide
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .nonRippleClickable { isPasswordVisible = !isPasswordVisible }
                )
            },
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        ) {
            IDStorageCheckBox(isChecked) { isChecked = !isChecked }
            Spacer(modifier = Modifier.weight(1f))

            Text(
                "아이디/비밀번호 찾기",
                style = TextStyle(
                    color = Chapter1TextGray,
                    fontSize = 14.sp,
                    fontFamily = pretendard,
                    lineHeight = 22.sp,
                    letterSpacing = -(0.025).em
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        } // Row
        CommonRoundedButton(
            text = "로그인",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) { }
    } // Column
}

@Composable
fun SocialLoginForm() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 48.dp)
    ) {
        SmallSocialLoginButton(
            iconRes = R.drawable.ic_kakao_logo
        ) { }

        SmallSocialLoginButton(
            iconRes = R.drawable.ic_google_logo
        ) { }

        SmallSocialLoginButton(
            iconRes = R.drawable.ic_apple_logo
        ) { }
    }
}

@Composable
fun IDStorageCheckBox(
    isChecked: Boolean,
    onCheckedChange: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.nonRippleClickable(onCheckedChange)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(22.dp)
                .background(
                    color = if (isChecked) Chapter1MainColor else Chapter1Black,
                    shape = RoundedCornerShape(6.dp)
                )
        ) {
            Image(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                colorFilter = if (isChecked) ColorFilter.tint(Color.White) else null
            )
        }

        Text(
            "아이디 저장",
            style = TextStyle(
                color = Chapter1TextGray,
                fontSize = 14.sp,
                fontFamily = pretendard,
                lineHeight = 22.sp,
                letterSpacing = -(0.025).em
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun EmailLoginScreenPreview() {
    PreviewContainer {
        EmailLoginScreen()
    }
}