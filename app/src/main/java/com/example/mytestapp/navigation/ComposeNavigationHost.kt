package com.example.mytestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun ComposeNavigationHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Chapter1Screen.Intro
    ) {
        chapter1Graph(navHostController)
    }
}