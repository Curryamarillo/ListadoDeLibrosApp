package com.example.listadodelibrosapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@Composable
fun Navigation(bookViewModel: BookViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.route,
    ) {
        composable(Routes.MainScreen.route) {
            MainScreen(navController, bookViewModel)
        }

        composable(Routes.BookInfo.route) {
            BookInfo(navController, bookViewModel)
        }
    }
}