package com.example.mytestapp.repository

import com.example.mytestapp.client.UserClient
import com.example.mytestapp.database.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val client: UserClient
): UserRepository {
    override fun insertUser(user: User): Flow<Int> = flow {
        if (user.name.isEmpty() || user.name.length < 2) {
            throw Exception("이름은 2자 이상이어야 합니다.")
        }

        if(user.email.isEmpty() || !isEmailValid(user.email)) {
            throw Exception("이메일 형식이 올바르지 않습니다.")
        }

        if(user.password.isEmpty() || user.password.length < 4) {
            throw Exception("비밀번호는 4자 이상이어야 합니다.")
        }

        client.insertUser(user)
            .onSuccess { emit(it) }
            .onFailure {
                it.printStackTrace()
                throw Exception("회원가입 실패")
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

    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return emailRegex.matches(email)
    }

    override fun updateInterest(value: String): Flow<Unit> = flow {
        client.updateInterest(value)
            .onSuccess { emit(Unit) }
            .onFailure {
                it.printStackTrace()
                throw Exception("관심사 업데이트 실패")
            }
    }

    override fun fetchInterest(uid: Int): Flow<String> = flow {
        client.fetchInterest(uid)
            .onSuccess { emit(it) }
            .onFailure {
                it.printStackTrace()
                throw Exception("관심사 조회 실패")
            }
    }

}