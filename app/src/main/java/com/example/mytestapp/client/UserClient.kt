package com.example.mytestapp.client

import com.example.mytestapp.database.dao.UserDao
import com.example.mytestapp.database.entity.User
import javax.inject.Inject

class UserClient @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) = runCatching {
        userDao.insertUser(user)
    }

    suspend fun getUserByEmail(email: String) = runCatching {
        userDao.getUserByEmail(email)
    }
}