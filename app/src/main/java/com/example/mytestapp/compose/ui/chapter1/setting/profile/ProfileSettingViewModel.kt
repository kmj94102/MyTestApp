package com.example.mytestapp.compose.ui.chapter1.setting.profile

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileSettingViewModel @Inject constructor(
    private val repository: UserRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val uid = savedStateHandle.toRoute<Chapter1Screen.Profile>().uid

    private val _uiState = MutableStateFlow<ProfileSettingUiState>(ProfileSettingUiState.Idle)
    val uiState: StateFlow<ProfileSettingUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ProfileSettingUiState.Idle
    )

    private val _state = mutableStateOf(ProfileSettingState())
    val state: State<ProfileSettingState> = _state

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        repository
            .fetchUserInfo(uid)
            .onEach {
                _state.value = _state.value.copy(
                    name = it.name,
                    profileName = it.profileName,
                    email = it.email,
                    country = it.country,
                    gender = it.gender,
                    phoneNumber = it.phoneNumber
                )
            }
            .catch {
                _uiState.value = ProfileSettingUiState.Error(it.message ?: "알 수 없는 에러")
            }
            .onCompletion {
                viewModelScope.launch {
                    delay(300)
                    _uiState.value = ProfileSettingUiState.Idle
                }
            }
            .launchIn(viewModelScope)
    }

    fun onNameChange(name: String) {
        if (name.length > 10) return
        _state.value = _state.value.copy(name = name)
    }

    fun onProfileNameChange(profileName: String) {
        if (profileName.length > 10) return
        _state.value = _state.value.copy(profileName = profileName)
    }

    fun onEmailChange(email: String) {
        if (email.length > 30) return
        _state.value = _state.value.copy(email = email)
    }

    fun onCountryChange(country: String) {
        _state.value = _state.value.copy(country = country)
    }

    fun onGenderChange(gender: String) {
        _state.value = _state.value.copy(gender = gender)
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        if (phoneNumber.length > 11 || !phoneNumber.all { it.isDigit() }) return
        _state.value = _state.value.copy(phoneNumber = phoneNumber)
    }

    fun onPass() {
        _uiState.value = ProfileSettingUiState.Pass(uid)
    }

    fun onSetting() {
        if (!_state.value.isAllFilled()) return

        repository
            .updateProfile(uid = uid, item = _state.value)
            .onEach {
                _uiState.value = ProfileSettingUiState.Success(uid)
            }
            .catch {
                _uiState.value = ProfileSettingUiState.Error(it.message ?: "알 수 없는 에러")
            }
            .onCompletion {
                viewModelScope.launch {
                    delay(300)
                    _uiState.value = ProfileSettingUiState.Idle
                }
            }
            .launchIn(viewModelScope)
    }

}

data class ProfileSettingState(
    val name: String = "",
    val profileName: String = "",
    val email: String = "",
    val country: String = "한국",
    val gender: String = "",
    val phoneNumber: String = ""
) {
    fun isAllFilled(): Boolean {
        return !(name.isEmpty() || profileName.isEmpty() || email.isEmpty() || country.isEmpty() ||
                gender.isEmpty() || phoneNumber.isEmpty())
    }
}

@Stable
sealed interface ProfileSettingUiState {
    object Idle : ProfileSettingUiState
    data class Success(
        val uid: Int
    ) : ProfileSettingUiState

    data class Error(
        val message: String
    ) : ProfileSettingUiState

    data class Pass(
        val uid: Int
    ) : ProfileSettingUiState

}