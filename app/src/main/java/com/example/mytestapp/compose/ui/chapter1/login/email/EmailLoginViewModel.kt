package com.example.mytestapp.compose.ui.chapter1.login.email

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EmailLoginViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<EmailLoginUiState>(EmailLoginUiState.Idle)
    val uiState: StateFlow<EmailLoginUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        EmailLoginUiState.Idle
    )

    private val _state = mutableStateOf(EmailLoginState())
    val state: State<EmailLoginState> = _state

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onPasswordVisibilityChange() {
        _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
    }

    fun onCheckedChange() {
        _state.value = _state.value.copy(isChecked = !_state.value.isChecked)
    }

    fun onLoginClick() {
        _uiState.value = EmailLoginUiState.Loading
        _uiState.value = EmailLoginUiState.Success
    }
}

data class EmailLoginState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isChecked: Boolean = false
)

@Stable
sealed interface EmailLoginUiState {
    data object Idle: EmailLoginUiState

    data object Loading: EmailLoginUiState

    data object Success: EmailLoginUiState

    data class Error(
        val message: String
    ): EmailLoginUiState
}