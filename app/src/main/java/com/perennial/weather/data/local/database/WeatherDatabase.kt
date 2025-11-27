package com.perennial.weather.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.perennial.weather.data.local.dao.UserDao
import com.perennial.weather.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}