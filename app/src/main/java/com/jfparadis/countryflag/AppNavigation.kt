package com.jfparadis.countryflag

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.jfparadis.countryflag.screens.CountriesScreen
import com.jfparadis.countryflag.screens.CountryDetailScreen

// This is the implementation of the navigation.
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            CountriesScreen(onItemClick = { item ->
                navController.navigate("detail/$item")
            })
        }
        composable(
            "detail/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item")
            CountryDetailScreen(countryName = item ?: "Unknown"){ navController.popBackStack() }
        }
    }
}