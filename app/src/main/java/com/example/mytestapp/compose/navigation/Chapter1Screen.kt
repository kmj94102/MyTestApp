package com.example.mytestapp.compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mytestapp.compose.ui.chapter1.IntroScreen
import com.example.mytestapp.compose.ui.chapter1.find.code.CodeCheckScreen
import com.example.mytestapp.compose.ui.chapter1.find.password.FindPasswordScreen
import com.example.mytestapp.compose.ui.chapter1.login.LoginScreen
import com.example.mytestapp.compose.ui.chapter1.login.email.EmailLoginScreen
import com.example.mytestapp.compose.ui.chapter1.setting.interest.InterestScreen
import com.example.mytestapp.compose.ui.chapter1.setting.profile.ProfileSettingCompleteScreen
import com.example.mytestapp.compose.ui.chapter1.setting.profile.ProfileSettingScreen
import com.example.mytestapp.compose.ui.chapter1.signup.SignUpScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.chapter1Graph(navHostController: NavHostController) {
    composable<Chapter1Screen.Intro> {
        IntroScreen(navHostController)
    }

    composable<Chapter1Screen.Login> {
        LoginScreen(navHostController)
    }

    composable<Chapter1Screen.EmailLogin> {
        EmailLoginScreen(navHostController)
    }

    composable<Chapter1Screen.SingUp> {
        SignUpScreen(navHostController)
    }

    composable<Chapter1Screen.Interest> {
        InterestScreen(navHostController)
    }

    composable<Chapter1Screen.Profile> {
        ProfileSettingScreen(navHostController)
    }

    composable<Chapter1Screen.ProfileComplete> {
        ProfileSettingCompleteScreen(navHostController)
    }

    composable<Chapter1Screen.FindPassword> {
        FindPasswordScreen(navHostController)
    }

    composable<Chapter1Screen.CodeCheck> {
        CodeCheckScreen(navHostController)
    }
}

sealed interface Chapter1Screen {
    @Serializable
    data object Intro : Chapter1Screen

    @Serializable
    data object Login: Chapter1Screen

    @Serializable
    data object EmailLogin: Chapter1Screen

    @Serializable
    data object SingUp: Chapter1Screen

    @Serializable
    data class Interest(
        val uid: Int
    ): Chapter1Screen

    @Serializable
    data class Profile(
        val uid: Int
    ): Chapter1Screen

    @Serializable
    data class ProfileComplete(
        val uid: Int
    ): Chapter1Screen

    @Serializable
    data object FindPassword: Chapter1Screen

    @Serializable
    data object CodeCheck: Chapter1Screen

}