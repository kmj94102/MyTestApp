package com.example.mytestapp.compose.ui.chapter1.setting.interest

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InterestViewModel @Inject constructor(
    private val repository: UserRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val uid = savedStateHandle.toRoute<Chapter1Screen.Interest>().uid

    private val _uiState = MutableStateFlow<InterestUiState>(InterestUiState.Idle)
    val uiState: StateFlow<InterestUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        InterestUiState.Idle
    )

    private val _state = mutableStateOf(InterestState())
    val state: State<InterestState> = _state

    init {
        fetchInterest()
    }

    private fun fetchInterest() {
        repository
            .fetchInterest(uid)
            .onEach { _state.value = _state.value.copy(selectList = it.split(","))}
            .catch { _uiState.value = InterestUiState.Error(it.message ?: "관심사 조회 실패") }
            .launchIn(viewModelScope)
    }

    fun updateSelectList(item: String) {
        val newList = _state.value.selectList.toMutableList()
        if(newList.contains(item)) {
            newList.remove(item)
        } else if(newList.size < 7) {
            newList.add(item)
        }
        _state.value = _state.value.copy(selectList = newList)
    }

    fun onPass() {
        _uiState.value = InterestUiState.Pass(uid)
    }

    fun onSave() {
        repository
            .updateInterest(_state.value.selectList.joinToString(","))
            .onEach { _uiState.value = InterestUiState.Success(uid) }
            .catch { _uiState.value = InterestUiState.Error(it.message ?: "관심사 업데이트 실패") }
            .launchIn(viewModelScope)
    }

}

data class InterestState(
    val list: List<String> = listOf(
        "가상화폐", "금리", "부동산", "투자", "10억", "부자 되는 방법", "제테크", "갭투자",
        "타이밍", "비트코인", "리플", "이더리움", "주식투자", "계좌개설", "코인", "도지코인",
        "안전거래", "대출", "ETF", "레버리지", "미국 나스닥", "펀드", "S&P500", "트론",
        "에이다", "스텔라루멘", "이오스"
    ),
    val selectList: List<String> = emptyList()
)

@Stable
sealed interface InterestUiState {
    object Idle: InterestUiState
    data class Success(
        val uid: Int
    ): InterestUiState

    data class Pass(
        val uid: Int
    ): InterestUiState

    data class Error(
        val message: String
    ): InterestUiState
}