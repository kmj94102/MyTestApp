package com.example.mytestapp.compose.ui.chapter1.login.email

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailLoginViewModel @Inject constructor(
    private val repository: UserRepository
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

    // todo: 아이디 저장 기능 구현
    fun onCheckedChange() {
        _state.value = _state.value.copy(isChecked = !_state.value.isChecked)
    }

    fun onLoginClick() {
        if(_uiState.value == EmailLoginUiState.Loading) {
            return
        }

        repository
            .login(_state.value.email, _state.value.password)
            .onStart {
                _uiState.value = EmailLoginUiState.Loading
            }
            .onEach {
                _uiState.value = EmailLoginUiState.Success(it.uid)
            }
            .catch {
                _uiState.value = EmailLoginUiState.Error(it.message ?: "알 수 없는 에러입니다.")
            }
            .onCompletion {
                viewModelScope.launch {
                    delay(300)
                    _uiState.value = EmailLoginUiState.Idle
                }
            }
            .launchIn(viewModelScope)
    }
}

data class EmailLoginState(
    val email: String = "test@naver.com",
    val password: String = "1234",
    val isPasswordVisible: Boolean = false,
    val isChecked: Boolean = false
)

@Stable
sealed interface EmailLoginUiState {
    data object Idle: EmailLoginUiState

    data object Loading: EmailLoginUiState

    data class Success(
        val uid: Int
    ): EmailLoginUiState

    data class Error(
        val message: String
    ): EmailLoginUiState
}