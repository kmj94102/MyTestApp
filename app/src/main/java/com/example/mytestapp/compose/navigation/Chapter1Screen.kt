package com.example.mytestapp.compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mytestapp.compose.ui.chapter1.IntroScreen
import com.example.mytestapp.compose.ui.chapter1.login.LoginScreen
import com.example.mytestapp.compose.ui.chapter1.login.email.EmailLoginScreen
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

}