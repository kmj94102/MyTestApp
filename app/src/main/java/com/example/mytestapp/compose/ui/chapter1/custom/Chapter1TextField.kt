package com.example.mytestapp.compose.ui.chapter1.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mytestapp.compose.theme.Chapter1Black
import com.example.mytestapp.compose.theme.Chapter1Hint
import com.example.mytestapp.compose.theme.Chapter1MainColor
import com.example.mytestapp.compose.theme.Chapter1TextGray
import com.example.mytestapp.compose.theme.pretendard

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onTextChange: (String) -> Unit,
    label: String = "",
    textStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 14.sp,
        fontFamily = pretendard,
        lineHeight = 20.sp,
        letterSpacing = -(0.025).em,
    ),
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None, // PasswordVisualTransformation(),
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: @Composable ((Boolean) -> Unit)? = null,
    trailingIcon: @Composable ((Boolean) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused }
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = TextStyle(
                    color = Chapter1TextGray,
                    fontSize = 14.sp,
                    fontFamily = pretendard,
                    lineHeight = 20.sp,
                    letterSpacing = -(0.025).em,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(if (isFocused) 1.dp else (-1).dp, Chapter1MainColor, RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) Chapter1MainColor.copy(alpha = 0.1f) else Chapter1Black,
                    RoundedCornerShape(16.dp)
                )
                .heightIn(min = 60.dp)
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            if(leadingIcon != null) {
                leadingIcon(isFocused)
                Spacer(modifier = Modifier.width(6.dp))
            }

            BasicTextField(
                value = value,
                onValueChange = onTextChange,
                maxLines = maxLines,
                singleLine = singleLine,
                readOnly = readOnly,
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = value,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = visualTransformation,
                        interactionSource = interactionSource,
                        placeholder = {
                            Text(
                                text = hint,
                                style = TextStyle(
                                    color = Chapter1Hint,
                                    fontSize = 14.sp,
                                    fontFamily = pretendard,
                                    lineHeight = 20.sp,
                                    letterSpacing = -(0.025).em,
                                )
                            )
                        },
                        contentPadding = contentPadding,
                        shape = RoundedCornerShape(3.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent
                        )
                    )
                },
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(Chapter1MainColor),
                modifier = Modifier.weight(1f)
            )

            if(trailingIcon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                trailingIcon(isFocused)
            }
        }
    }
}