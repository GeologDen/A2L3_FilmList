package com.geekbrains.weatherwithmvvm.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.weatherwithmvvm.R
import com.geekbrains.weatherwithmvvm.databinding.FragmentDetailsBinding
import com.geekbrains.weatherwithmvvm.model.entities.movieCard

@Suppress("NAME_SHADOWING")
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieCard = arguments?.getParcelable<movieCard>(BUNDLE_EXTRA)
        movieCard?.let { movieCard1 ->
            val movie = movieCard1.movie
            binding.cityName.text = movie.film
            binding.cityCoordinates.text = String.format(
                    getString(R.string.movie_year_rating_label),
                    movie.releaseYear.toString(),
                    movie.movieRating.toString()
            )
            binding.temperatureValue.text = movieCard1.filmGenre
            binding.feelsLikeValue.text = movieCard1.filmDuration.toString()
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "movieCard"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}