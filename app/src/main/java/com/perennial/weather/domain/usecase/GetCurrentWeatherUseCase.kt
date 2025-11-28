package com.perennial.weather.domain.usecase

import com.perennial.weather.data.local.entity.WeatherEntity
import com.perennial.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<WeatherEntity?> {
        return try {
            repository.fetchAndSaveWeather(lat, lon)
            val weather = repository.getLatestWeather()
            Result.success(weather)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

