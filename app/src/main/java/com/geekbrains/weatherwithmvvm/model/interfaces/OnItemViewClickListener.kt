package com.geekbrains.weatherwithmvvm.model.interfaces

import com.geekbrains.weatherwithmvvm.model.entities.movieCard

interface OnItemViewClickListener {
    fun onItemViewClick(movieCard: movieCard)
}