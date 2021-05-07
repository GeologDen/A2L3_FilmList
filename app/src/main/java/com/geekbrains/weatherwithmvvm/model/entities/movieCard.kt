package com.geekbrains.weatherwithmvvm.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class movieCard(
    val movie: Movie = getDefaultMovie(),
    val filmGenre: String = "",
    val filmDuration: Int = 0
) : Parcelable

fun getDefaultMovie() = Movie("The Lord of the Rings", 2000, 5.0)

fun getWorldMovies(): List<movieCard> {
    return listOf(
            movieCard(Movie("The Lord of the Rings", 2000, 5.0), "Фэнтези", 108),
            movieCard(Movie("Сплит", 2001, 4.0), "Триллер", 109),
            movieCard(Movie("Назад в будущее", 2002, 3.0), "Приключения", 110),

    )
}

fun getRussianMovies(): List<movieCard> {
    return listOf(
            movieCard(Movie("Операция Ы", 2020, 2.0), "Комедия", 112),
            movieCard(Movie("Брилиантовая рука", 2019, 1.0), "Комедия", 113),
            movieCard(Movie("Самогонщики", 2018, 0.5), "Драма", 114),

    )
}