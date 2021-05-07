package com.geekbrains.weatherwithmvvm.model

import com.geekbrains.weatherwithmvvm.model.entities.movieCard

interface Repository {
    fun getWeatherFromServer(): movieCard
    fun getWeatherFromLocalStorageRus(): List<movieCard>
    fun getWeatherFromLocalStorageWorld(): List<movieCard>
}