package com.geekbrains.weatherwithmvvm.model

import com.geekbrains.weatherwithmvvm.model.entities.movieCard
//Просто сделан замену имен переменных
sealed class AppState {
    data class Success(val movieCardData: List<movieCard>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
