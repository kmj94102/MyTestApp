package com.example.mytestapp.di

import android.app.Application
import androidx.room.Room
import com.example.mytestapp.database.TestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): TestDatabase = Room
        .databaseBuilder(
            application,
            TestDatabase::class.java,
            "test_database"
        )
        .fallbackToDestructiveMigration(false)
        .build()

    @Provides
    @Singleton
    fun provideUserDao(database: TestDatabase) = database.userDao()
}