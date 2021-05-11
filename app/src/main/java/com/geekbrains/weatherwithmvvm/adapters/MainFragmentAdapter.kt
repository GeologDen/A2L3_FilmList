package com.geekbrains.weatherwithmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.weatherwithmvvm.databinding.ItemListCitiesBinding
import com.geekbrains.weatherwithmvvm.model.entities.movieCard
import com.geekbrains.weatherwithmvvm.model.interfaces.OnItemViewClickListener

class MainFragmentAdapter(private val itemClickListener: OnItemViewClickListener)
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var movieCardData: List<movieCard> = listOf()
    private lateinit var binding: ItemListCitiesBinding

    fun setWeather(data: List<movieCard>) {
        movieCardData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainViewHolder {
        binding = ItemListCitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieCardData[position])
    }

    override fun getItemCount() = movieCardData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movieCard: movieCard) = with(binding) {
            mainFragmentRecyclerItemTextView.text = movieCard.movie.film
            root.setOnClickListener {
                itemClickListener.onItemViewClick(movieCard)
            }
        }
    }
}