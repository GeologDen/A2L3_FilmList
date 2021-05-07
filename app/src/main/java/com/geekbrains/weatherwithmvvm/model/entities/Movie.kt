package com.geekbrains.weatherwithmvvm.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Просто сделан замену имен переменных
@Parcelize
data class Movie(
    val film: String,
    val releaseYear: Int,
    val movieRating: Double
) : Parcelable
