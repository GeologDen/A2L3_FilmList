package com.geekbrains.weatherwithmvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.weatherwithmvvm.R
import com.geekbrains.weatherwithmvvm.adapters.MainFragmentAdapter
import com.geekbrains.weatherwithmvvm.databinding.MainFragmentBinding
import com.geekbrains.weatherwithmvvm.model.AppState
import com.geekbrains.weatherwithmvvm.model.entities.movieCard
import com.geekbrains.weatherwithmvvm.model.interfaces.OnItemViewClickListener
import com.geekbrains.weatherwithmvvm.model.showSnackBar
import com.geekbrains.weatherwithmvvm.ui.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private var adapter: MainFragmentAdapter? = null
    private var isDataSetRus: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeMovieDataSet() }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMovieFromLocalSourceRus()
    }

    private fun changeMovieDataSet() = with(binding) {
        if (isDataSetRus) {
            viewModel.getMovieFromLocalSourceWorld()
            mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getMovieFromLocalSourceRus()
            mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetRus = !isDataSetRus
    }

    @Suppress("NAME_SHADOWING")
    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(movieCard: movieCard) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, movieCard)
                            }
                            manager.beginTransaction()
                                    .add(R.id.container, DetailsFragment.newInstance(bundle))
                                    .addToBackStack("")
                                    .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setWeather(appState.movieCardData)
                }
                mainFragmentRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                mainFragmentFAB.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload),
                        { viewModel.getMovieFromLocalSourceRus() }
                )
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}