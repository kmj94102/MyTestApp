package com.example.mytestapp.repository

import com.example.mytestapp.client.UserClient
import com.example.mytestapp.compose.ui.chapter1.setting.profile.ProfileSettingState
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

    override fun fetchUserInfo(uid: Int): Flow<User> = flow {
        client.fetchUserInfo(uid)
            .onSuccess { emit(it) }
            .onFailure {
                it.printStackTrace()
                throw Exception("회원 정보 조회 실패")
            }
    }

    override fun updateProfile(uid: Int, item: ProfileSettingState): Flow<Unit> = flow {
        if (item.name.isEmpty() || item.name.length < 2) {
            throw Exception("이름은 2자 이상이어야 합니다.")
        }
        if (item.profileName.isEmpty() || item.profileName.length < 2) {
            throw Exception("프로필 명은 2자 이상이어야 합니다.")
        }
        if (item.email.isEmpty() || !isEmailValid(item.email)) {
            throw Exception("이메일 형식이 올바르지 않습니다.")
        }
        if (item.phoneNumber.length !in 10..11) {
            throw Exception("전화번호는 10자 또는 11자여야 합니다.")
        }
        if (item.gender != "남" && item.gender != "여") {
            throw Exception("성별은 남 또는 여여야 합니다.")
        }

        client.updateProfile(item, uid)
            .onSuccess { emit(Unit) }
            .onFailure {
                it.printStackTrace()
                throw Exception("프로필 업데이트 실패")
            }
    }

}