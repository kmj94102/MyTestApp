package com.example.mytestapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytestapp.database.dao.UserDao
import com.example.mytestapp.database.entity.User

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = true
)
abstract class TestDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}