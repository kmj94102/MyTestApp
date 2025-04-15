package com.example.mytestapp.compose.ui.chapter1.setting.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.data.chapter1.Country
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.compose.theme.Chapter1Background
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.unit.nonRippleClickable
import com.example.mytestapp.util.toast
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen(
    navHostController: NavHostController? = null,
    viewModel: ProfileSettingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var isSheetOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Chapter1GNB(
            title = "프로필 설정",
            onBackClick = { navHostController?.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(40.dp))

        ProfileImage()
        Spacer(Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                ProfileSettingItems(
                    state = viewModel.state.value,
                    onNameChange = viewModel::onNameChange,
                    onProfileNameChange = viewModel::onProfileNameChange,
                    onEmailChange = viewModel::onEmailChange,
                    onGenderChange = viewModel::onGenderChange,
                    onPhoneNumberChange = viewModel::onPhoneNumberChange,
                    onCountryClick = { isSheetOpen = true }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            CommonRoundedButton(
                text = "다음에",
                backgroundColor = Chapter1Black,
                textColor = Chapter1TextGray,
                modifier = Modifier.weight(1f),
                onClick = viewModel::onPass
            )

            CommonRoundedButton(
                text = "입력 완료",
                backgroundColor = if (viewModel.state.value.isAllFilled()) {
                    Chapter1MainColor
                } else {
                    Chapter1Black
                },
                textColor = if (viewModel.state.value.isAllFilled()) {
                    Color.White
                } else {
                    Chapter1TextGray
                },
                modifier = Modifier.weight(1f),
                onClick = viewModel::onSetting
            )
        }
    }

    CountrySelector(
        sheetState = sheetState,
        isOpen = isSheetOpen,
        onDismiss = {
            scope.launch {
                sheetState.hide()
                isSheetOpen = false
            }
        },
        value = viewModel.state.value.country,
        onValueChange = viewModel::onCountryChange,
        modifier = Modifier.fillMaxWidth()
    )

    LaunchedEffect(uiState) {
        when(uiState) {
            is ProfileSettingUiState.Success -> {
                navHostController?.navigate(
                    Chapter1Screen.ProfileComplete(
                        (uiState as ProfileSettingUiState.Success).uid
                    )
                )
            }
            is ProfileSettingUiState.Pass -> {
                navHostController?.navigate(
                    Chapter1Screen.ProfileComplete(
                        (uiState as ProfileSettingUiState.Pass).uid
                    )
                )
            }
            is ProfileSettingUiState.Error -> {
                context.toast((uiState as ProfileSettingUiState.Error).message)
            }
            else -> {}
        }
    }
}

@Composable
fun ProfileImage() {
    Box {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .background(Chapter1Black, CircleShape)
        ) {
            // todo 이미지 선택 추가하기
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 6.dp, end = 6.dp)
                .size(24.dp)
                .background(Chapter1MainColor, RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.ic_modify),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
fun ProfileSettingItems(
    state: ProfileSettingState,
    onNameChange: (String) -> Unit = {},
    onProfileNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onGenderChange: (String) -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    onCountryClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonTextField(
            value = state.name,
            onTextChange = onNameChange,
            hint = "이름을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = state.profileName,
            onTextChange = onProfileNameChange,
            hint = "프로필 명을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = state.email,
            onTextChange = onEmailChange,
            hint = "이메일을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = state.phoneNumber,
            onTextChange = onPhoneNumberChange,
            hint = "전화번호를 입력해 주세요",
            leadingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.nonRippleClickable(onCountryClick)
                ) {
                    Image(
                        painter = painterResource(Country.getCountryImage(state.country)),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp, 17.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_down),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // todo 성별 선택 추가하기
        CommonTextField(
            value = state.gender,
            onTextChange = onGenderChange,
            hint = "성별을 선택해 주세요",
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_down),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelector(
    isOpen: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {
    var temp by remember { mutableStateOf(value) }
    if (isOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss,
            dragHandle = null,
            containerColor = Chapter1Background,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            modifier = modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(
                    "국가를 선택하세요",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = pretendard,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        letterSpacing = (-0.025).em
                    )
                )
                Spacer(Modifier.height(8.dp))

                Country.entries.forEach {
                    CountrySelectorItem(
                        text = it.koreaName,
                        imageRes = it.imageRes,
                        isSelect = temp == it.koreaName
                    ) { temp = it.koreaName }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CommonRoundedButton(
                        text = "취소",
                        backgroundColor = Chapter1Black,
                        textColor = Chapter1TextGray,
                        modifier = Modifier.weight(1f),
                        onClick = onDismiss
                    )

                    CommonRoundedButton(
                        text = "확인",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onValueChange(temp)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CountrySelectorItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes
    imageRes: Int,
    isSelect: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (isSelect) Chapter1MainColor.copy(alpha = 0.1f) else Chapter1Background,
                RoundedCornerShape(16.dp)
            )
            .border(
                if (isSelect) 1.dp else (-1).dp,
                Chapter1MainColor,
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .nonRippleClickable(onClick)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.size(32.dp, 24.dp)
        )
        Text(
            text,
            style = TextStyle(
                color = Color.White,
                fontFamily = pretendard,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                letterSpacing = (-0.025).em
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        if (isSelect) {
            Image(
                painter = painterResource(R.drawable.ic_check_main_color),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun ProfileSettingScreenPreview() {
    PreviewContainer {
        ProfileSettingScreen()
    }
}