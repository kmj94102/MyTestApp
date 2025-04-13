package com.example.mytestapp.compose.ui.chapter1.signup

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.database.entity.User
import com.example.mytestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SinUpViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<SinUpUiState>(SinUpUiState.Idle)
    val uiState: StateFlow<SinUpUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        SinUpUiState.Idle
    )

    private val _state = mutableStateOf(SinUpState())
    val state: State<SinUpState> = _state

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onPasswordVisibilityChange() {
        _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
    }

    fun onSinUp() {
        repository
            .insertUser(
                User.newUser(
                    email = _state.value.email,
                    password = _state.value.password,
                    name = _state.value.name
                )
            )
            .onStart {
                _uiState.value = SinUpUiState.Loading
            }
            .onEach { _uiState.value = SinUpUiState.Success(it) }
            .catch { _uiState.value = SinUpUiState.Error(it.message ?: "알 수 없는 에러입니다.") }
            .launchIn(viewModelScope)
    }
}

data class SinUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)

@Stable
sealed interface SinUpUiState {
    data object Idle: SinUpUiState
    data object Loading: SinUpUiState
    data class Success(
        val uid: Int
    ): SinUpUiState
    data class Error(val message: String): SinUpUiState
}