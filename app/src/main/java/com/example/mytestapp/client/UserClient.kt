package com.example.mytestapp.client

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

}