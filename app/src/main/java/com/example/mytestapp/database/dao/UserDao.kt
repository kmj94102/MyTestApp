package com.example.mytestapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestapp.database.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("UPDATE user SET interests = :value")
    suspend fun updateInterest(value: String)

    @Query("SELECT interests FROM user WHERE uid = :uid")
    suspend fun fetchInterest(uid: Int): String

}