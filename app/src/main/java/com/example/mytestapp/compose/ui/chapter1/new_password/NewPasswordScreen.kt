package com.example.mytestapp.compose.ui.chapter1.new_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.unit.nonRippleClickable

@Composable
fun NewPasswordScreen() {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "새로운 비밀번호",
            onBackClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "새로운 비밀번호를 입력해 주세요\n이전에 사용하셨던 비밀번호는 사용 하실 수 없습니다.",
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Chapter1TextGray,
                letterSpacing = (-0.025).em,
                lineHeight = 20.sp
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        CommonTextField(
            value = password,
            onTextChange = { password = it },
            label = "새로운 비밀번호",
            hint = "비밀번호를 입력해 주세요",
            visualTransformation = if (isPasswordVisible) {
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
            modifier = Modifier.padding(top = 40.dp)
        )

        CommonTextField(
            value = confirmPassword,
            onTextChange = { confirmPassword = it },
            label = "비밀번호 재입력",
            hint = "비밀번호를 입력해 주세요",
            visualTransformation = if (isConfirmPasswordVisible) {
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
                        if (isConfirmPasswordVisible) {
                            R.drawable.ic_show
                        } else {
                            R.drawable.ic_hide
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .nonRippleClickable { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                )
            },
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(Modifier.weight(1f))

        CommonRoundedButton(
            text = "재설정 완료",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) { }
    }
}

@Preview
@Composable
fun NewPasswordScreenPreview() {
    PreviewContainer {
        NewPasswordScreen()
    }
}