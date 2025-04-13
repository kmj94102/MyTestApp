package com.example.mytestapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val email: String,
    val password: String,
    val name: String,
    val profileName: String,
    val isBiometricUsed: Boolean,
    val interests: String,
    val phoneNumber: String,
    val country: String,
    val gender: String,
) {
    companion object {
        fun newUser(email: String, password: String, name: String) = User(
            email = email,
            password = password,
            name = name,
            profileName = "",
            isBiometricUsed = false,
            interests = "",
            phoneNumber = "",
            country = "",
            gender = ""
        )
    }
}
