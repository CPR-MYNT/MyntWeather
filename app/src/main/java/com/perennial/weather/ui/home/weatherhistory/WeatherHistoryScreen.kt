package com.perennial.weather.ui.home.weatherhistory

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.perennial.weather.ui.home.HomeViewModel

@Composable
fun WeatherHistoryScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
){
    Text("Weather history")
}