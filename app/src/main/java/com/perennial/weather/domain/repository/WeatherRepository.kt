package com.perennial.weather.domain.repository

import com.perennial.weather.data.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchAndSaveWeather(lat: Double, lon: Double)
    suspend fun getLatestWeather(): WeatherEntity?
    fun getWeatherHistory(): Flow<List<WeatherEntity>>
}

