package com.example.myapplication.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.model.User
import com.example.myapplication.data.remote.KtorApiClient
import com.example.myapplication.ui.screens.MainScreen
import com.example.myapplication.ui.screens.UserDetailScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    val scope = rememberCoroutineScope()

    // Consultar API solo una vez
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = KtorApiClient.getUsers()
                users = response.users
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        // Pantalla principal con lista de usuarios
        composable(route = Screen.Main.route) {
            MainScreen(
                users = users,
                onUserClick = { userId ->
                    navController.navigate(Screen.Detail.createRoute(userId))
                }
            )
        }

        // Pantalla de detalle del usuario
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            val user = users.find { it.id == userId }

            UserDetailScreen(
                user = user,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

