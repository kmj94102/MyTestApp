package com.example.mytestapp.compose.ui.chapter1

import androidx.lifecycle.ViewModel
import com.example.mytestapp.compose.data.chapter1.Intro
import com.example.mytestapp.compose.data.chapter1.fetchIntroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(): ViewModel() {
    private val _list = MutableStateFlow<List<Intro>>(fetchIntroList())
    val list: List<Intro> = _list.value
}