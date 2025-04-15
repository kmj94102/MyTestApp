package com.example.mytestapp.repository

import com.example.mytestapp.compose.ui.chapter1.setting.profile.ProfileSettingState
import com.example.mytestapp.database.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun insertUser(user: User): Flow<Int>

    fun login(email: String, password: String): Flow<User>

    fun updateInterest(value: String): Flow<Unit>

    fun fetchInterest(uid: Int): Flow<String>

    fun fetchUserInfo(uid: Int): Flow<User>

    fun updateProfile(uid: Int, item: ProfileSettingState): Flow<Unit>
}