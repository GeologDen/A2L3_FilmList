package com.geekbrains.weatherwithmvvm.model.interfaces

import com.geekbrains.weatherwithmvvm.model.entities.movieCard
//Просто сделан замену имен переменных
interface OnItemViewClickListener {
    fun onItemViewClick(movieCard: movieCard)
}