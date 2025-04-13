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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mytestapp.R
import com.example.mytestapp.compose.PreviewContainer
import com.example.mytestapp.compose.theme.Chapter1Background
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard
import com.example.mytestapp.compose.ui.chapter1.custom.Chapter1GNB
import com.example.mytestapp.compose.ui.chapter1.custom.CommonRoundedButton
import com.example.mytestapp.compose.ui.chapter1.custom.CommonTextField
import com.example.mytestapp.compose.unit.nonRippleClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen(
    navHostController: NavHostController? = null
) {
    var country by remember { mutableStateOf("한국") }
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
                modifier = Modifier.weight(1f)
            ) { }

            CommonRoundedButton(
                text = "입력 완료",
                backgroundColor = Chapter1Black,
                textColor = Chapter1TextGray,
                modifier = Modifier.weight(1f)
            ) { }
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
        value = country,
        onValueChange = { country = it },
        modifier = Modifier.fillMaxWidth()
    )
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
    onCountryClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonTextField(
            value = "",
            onTextChange = {},
            hint = "이름을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = "",
            onTextChange = {},
            hint = "프로필 명을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = "",
            onTextChange = {},
            hint = "이메일을 입력해 주세요",
            modifier = Modifier.fillMaxWidth()
        )

        CommonTextField(
            value = "",
            onTextChange = {},
            hint = "전화번호를 입력해 주세요",
            leadingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.nonRippleClickable(onCountryClick)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_korea),
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

        CommonTextField(
            value = "",
            onTextChange = {},
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

                CountrySelectorItem(
                    text = "한국",
                    imageRes = R.drawable.ic_korea,
                    isSelect = temp == "한국"
                ) { temp = "한국" }
                CountrySelectorItem(
                    text = "미국",
                    imageRes = R.drawable.ic_usa,
                    isSelect = temp == "미국"
                ) { temp = "미국" }
                CountrySelectorItem(
                    text = "인도네시아",
                    imageRes = R.drawable.ic_india,
                    isSelect = temp == "인도네시아"
                ) { temp = "인도네시아" }
                CountrySelectorItem(
                    text = "아르헨티나",
                    imageRes = R.drawable.img_argentina,
                    isSelect = temp == "아르헨티나"
                ) { temp = "아르헨티나" }
                CountrySelectorItem(
                    text = "이탈리아",
                    imageRes = R.drawable.ic_italy,
                    isSelect = temp == "이탈리아"
                ) { temp = "이탈리아" }
                CountrySelectorItem(
                    text = "캐나다",
                    imageRes = R.drawable.ic_canada,
                    isSelect = temp == "캐나다"
                ) { temp = "캐나다" }

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
        modifier = modifier.fillMaxWidth()
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
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp)
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