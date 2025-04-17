package com.example.mytestapp.compose.ui.chapter1.find.code

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodeCheckViewModel @Inject constructor() : ViewModel() {
    private var countdownJob: Job? = null

    private val _state = mutableStateOf(CodeCheckState())
    val state: State<CodeCheckState> = _state

    private val _uiState = MutableStateFlow<CodeCheckUiState>(CodeCheckUiState.Idle)
    val uiState: StateFlow<CodeCheckUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CodeCheckUiState.Idle
    )

    fun startCountdown() {
        if (countdownJob?.isActive == true) return

        countdownJob = viewModelScope.launch {
            while (_state.value.secondsLeft > 0) {
                delay(1000L)
                _state.value = _state.value.copy(secondsLeft = _state.value.secondsLeft - 1)
            }
        }
    }

    fun restartCountdown() {
        countdownJob?.cancel()
        _state.value = _state.value.copy(secondsLeft = 120)
        startCountdown()
    }

    fun updateCode(value: String) {
        value.trim().filter { value -> value.isDigit() }
        if (value.trim().length <= 4) {
            _state.value = _state.value.copy(code = value)
        }
    }

    fun checkCode() {
        if (_state.value.code != "1234") {
            _uiState.value = CodeCheckUiState.Error("코드를 확인해주세요.")
        } else {
            _uiState.value = CodeCheckUiState.Success
        }
    }

}

data class CodeCheckState(
    val code: String = "",
    val secondsLeft: Int = 120
)

@Stable
sealed interface CodeCheckUiState {
    data object Idle : CodeCheckUiState

    data object Success : CodeCheckUiState

    data class Error(val message: String) : CodeCheckUiState
}