package com.logixphere.essentialcode.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.logixphere.essentialcode.SplashScreen
import com.logixphere.essentialcode.ui.auth.SignInView
import com.logixphere.essentialcode.ui.auth.UserLogin
import com.logixphere.essentialcode.ui.home.DetailActivity
import com.logixphere.essentialcode.ui.home.HomeScreen
import javax.inject.Inject

sealed class AppScreen(val route: String) {
    data object INITIAL: AppScreen("initial_screen")
    data object LOGIN: AppScreen("login_screen")
    data object HOME: AppScreen("home_screen")
    data object DETAIL_MOVIE: AppScreen("detail_movie_screen")
    data object NOTIFIKASI: AppScreen("notifikasi_screen")
    data object PENGATURAN: AppScreen("pengaturan_screen")
}

@Composable
fun AppNavHost(prefs: SharedPrefUtil) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.INITIAL.route) {
        composable(AppScreen.INITIAL.route) {
            if(prefs.getAuthToken() != null) {
                HomeScreen(navController)
            } else {
                SplashScreen(navController)
            }
        }
        composable(AppScreen.LOGIN.route) {
            SignInView(navController)
        }
        composable(AppScreen.HOME.route) {
            HomeScreen(navController)
        }
        composable(AppScreen.DETAIL_MOVIE.route) {
            DetailActivity(navController)
        }
    }
}