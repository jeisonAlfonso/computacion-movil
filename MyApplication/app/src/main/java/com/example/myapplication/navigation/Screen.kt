package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{userId}") {
        fun createRoute(userId: Int) = "detail/$userId"
    }
}

