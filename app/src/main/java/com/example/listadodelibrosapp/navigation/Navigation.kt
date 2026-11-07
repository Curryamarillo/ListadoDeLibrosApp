package com.example.listadodelibrosapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadodelibrosapp.screens.BookInfo
import com.example.listadodelibrosapp.screens.MainScreen
import com.example.listadodelibrosapp.viewmodel.BookViewModel


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

        composable(
            route = Routes.BookInfo.route) {
                BookInfo(
                    navController,
                    bookViewModel
                )
        }
    }
}

