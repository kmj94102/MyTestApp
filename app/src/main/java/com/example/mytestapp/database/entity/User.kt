package com.example.mytestapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    val email: String,
    val password: String,
    val name: String,
    val profileName: String,
    val isBiometricUsed: Boolean,
    val interests: String,
    val phoneNumber: String,
    val country: String,
    val gender: String,
)
