package com.example.mytestapp.di

import com.example.mytestapp.client.UserClient
import com.example.mytestapp.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientModule {
    @Provides
    @Singleton
    fun bindUserClient(
        userDao: UserDao
    ): UserClient = UserClient(userDao)
}