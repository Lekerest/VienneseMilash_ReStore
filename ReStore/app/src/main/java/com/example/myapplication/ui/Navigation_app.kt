package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.App_Screen
import com.example.myapplication.MainScreen
import com.example.myapplication.OnBoard

@Composable
fun Navigation_app() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboard") {
        composable("onboard") {
            OnBoard(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
        composable(
            route = "app/{appId}",
            arguments = listOf(navArgument("appId") { type = NavType.IntType })
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getInt("appId")
            App_Screen(appId, navController)
        }
    }
}
