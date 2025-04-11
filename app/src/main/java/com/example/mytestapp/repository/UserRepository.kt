package com.example.mytestapp.repository

import com.example.mytestapp.database.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun insertUser(user: User): Flow<Boolean>

    fun login(email: String, password: String): Flow<User>
}