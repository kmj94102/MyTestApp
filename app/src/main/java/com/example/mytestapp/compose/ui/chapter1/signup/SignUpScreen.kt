package com.example.mytestapp.compose.ui.chapter1.signup

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.ui.chapter1.custom.SmallSocialLoginButton
import com.example.mytestapp.compose.unit.nonRippleClickable
import com.example.mytestapp.util.toast

@Composable
fun SignUpScreen(
    navHostController: NavHostController? = null,
    viewModel: SinUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "가입하기",
            onBackClick = { navHostController?.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            item {
                SignUpForm(
                    name = viewModel.state.value.name,
                    email = viewModel.state.value.email,
                    password = viewModel.state.value.password,
                    isPasswordVisible = viewModel.state.value.isPasswordVisible,
                    onNameChange = viewModel::onNameChange,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    onPasswordVisibilityChange = viewModel::onPasswordVisibilityChange,
                    onSinUpClick = viewModel::onSinUp
                )
            }

            item { SocialSignUpButtons() }
        }

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .nonRippleClickable { navHostController?.popBackStack() }
        ) {
            Text(
                "이미 계정이 있으신가요?",
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
                "로그인",
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

    LaunchedEffect(uiState) {
        when(uiState) {
            is SinUpUiState.Success -> {
                navHostController?.navigate(
                    Chapter1Screen.Interest(
                        uid = (uiState as SinUpUiState.Success).uid
                    )
                )
            }
            is SinUpUiState.Error -> {
                context.toast((uiState as SinUpUiState.Error).message)
            }
            else -> {}
        }
    }
}

@Composable
fun SignUpForm(
    name: String = "",
    email: String = "",
    password: String = "",
    isPasswordVisible: Boolean = false,
    onNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onPasswordVisibilityChange: () -> Unit = {},
    onSinUpClick: () -> Unit = {}
) {
    Column {
        CommonTextField(
            value = name,
            onTextChange = onNameChange,
            label = "이름",
            hint = "이름을 입력해 주세요",
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    colorFilter = if (it) ColorFilter.tint(Chapter1MainColor) else null,
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.padding(top = 24.dp)
        )

        CommonTextField(
            value = email,
            onTextChange = onEmailChange,
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
            modifier = Modifier.padding(top = 20.dp)
        )

        CommonTextField(
            value = password,
            onTextChange = onPasswordChange,
            visualTransformation = if(isPasswordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            label = "비밀번호",
            hint = "비밀번호를 입력해 주세요",
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
                        .nonRippleClickable(onPasswordVisibilityChange)
                )
            },
            modifier = Modifier.padding(top = 20.dp)
        )

        CommonRoundedButton(
            text = "회원가입",
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp)
        ) { onSinUpClick() }
    }
}

@Composable
fun SocialSignUpButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 48.dp)
    ) {
        SmallSocialLoginButton(
            iconRes = R.drawable.ic_kakao_logo_gray,
        ) { }

        SmallSocialLoginButton(
            iconRes = R.drawable.ic_google_logo,
            isFilter = true
        ) { }

        SmallSocialLoginButton(
            iconRes = R.drawable.ic_apple_logo,
            isFilter = true
        ) { }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    PreviewContainer {
        SignUpScreen()
    }
}