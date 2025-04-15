package com.example.mytestapp.compose.ui.chapter1.setting.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mytestapp.compose.navigation.Chapter1Screen
import com.example.mytestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileSettingCompleteViewModel @Inject constructor(
    repository: UserRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val uid = savedStateHandle.toRoute<Chapter1Screen.ProfileComplete>().uid
    val userInfo = repository.fetchUserInfo(uid)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}