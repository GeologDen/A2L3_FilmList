package com.geekbrains.weatherwithmvvm.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val film: String,
    val releaseYear: Int,
    val movieRating: Double
) : Parcelable
