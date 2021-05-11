package com.geekbrains.weatherwithmvvm.model

import com.geekbrains.weatherwithmvvm.model.entities.movieCard
import com.geekbrains.weatherwithmvvm.model.entities.getRussianMovies
import com.geekbrains.weatherwithmvvm.model.entities.getWorldMovies

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = movieCard()
    override fun getWeatherFromLocalStorageRus() = getRussianMovies()
    override fun getWeatherFromLocalStorageWorld() = getWorldMovies()
}