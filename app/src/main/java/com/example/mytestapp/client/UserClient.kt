package com.example.mytestapp.client

import com.example.mytestapp.compose.ui.chapter1.setting.profile.ProfileSettingState
import com.example.mytestapp.database.dao.UserDao
import com.example.mytestapp.database.entity.User
import javax.inject.Inject

class UserClient @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) = runCatching {
        userDao.insertUser(user).toInt()
    }

    suspend fun getUserByEmail(email: String) = runCatching {
        userDao.getUserByEmail(email)
    }

    suspend fun updateInterest(value: String) = runCatching {
        userDao.updateInterest(value)
    }

    suspend fun fetchInterest(uid: Int) = runCatching {
        userDao.fetchInterest(uid)
    }

    suspend fun fetchUserInfo(uid: Int) = runCatching {
        userDao.fetchUserInfo(uid)
    }

    suspend fun updateProfile(
        item: ProfileSettingState,
        uid: Int
    ) = runCatching {
        userDao.updateProfile(
            name = item.name,
            profileName = item.profileName,
            email = item.email,
            country = item.country,
            gender = item.gender,
            phoneNumber = item.phoneNumber,
            uid = uid
        )
    }

}