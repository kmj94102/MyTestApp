package com.example.mytestapp.repository

import com.example.mytestapp.client.UserClient
import com.example.mytestapp.database.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val client: UserClient
): UserRepository {
    override fun insertUser(user: User): Flow<Boolean> = flow {
        client.insertUser(user)
            .onSuccess { emit(true) }
            .onFailure {
                it.printStackTrace()
                emit(false)
            }
    }

    override fun login(email: String, password: String): Flow<User> = flow {
        client.getUserByEmail(email)
            .onSuccess {
                if (it?.password == password) {
                    emit(it)
                } else {
                    throw Exception("이메일 또는 비밀번호가 일치하지 않습니다.")
                }
            }
            .onFailure {
                it.printStackTrace()
                throw Exception("이메일이 존재하지 않습니다.")
            }
    }

}