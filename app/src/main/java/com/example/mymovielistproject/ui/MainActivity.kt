package com.example.mymovielistproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovielistproject.R
import com.example.mymovielistproject.adapters.MoviesAdapter
import com.example.mymovielistproject.databinding.ActivityMainBinding
import com.example.mymovielistproject.databinding.ActivityMovieDetailsBinding
import com.example.mymovielistproject.model.Movies
import com.example.mymovielistproject.utils.AppConstants
import com.example.mymovielistproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var moviesViewModel: MovieViewModel
    private var tTemp: String = "popular"
    private var tbool: Int = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieRecyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        getRecyclerViewData(R.id.popular_tab)

        binding.popularTab.setOnClickListener {
            getRecyclerViewData(R.id.popular_tab)
        }

        binding.topRatedTab.setOnClickListener { getRecyclerViewData(R.id.top_Rated_tab) }

        binding.NowPlayingTab.setOnClickListener { getRecyclerViewData(R.id.Now_Playing_tab) }


    }

    private fun getRecyclerViewData(id: Int) {

        val type: String = when (id) {

            R.id.popular_tab -> AppConstants.POPULAR

            R.id.top_Rated_tab -> AppConstants.TOP_RATED

            else -> AppConstants.NOW_PLAYING
        }

        if (type != tTemp) {
            tbool = 1
            tTemp = type
        }
        moviesViewModel.getMovies(type, tbool).observe(this, Observer {
            populateMovieRecycler(it)
        })
    }

    private fun populateMovieRecycler(moviesList: List<Movies>) {

        movieRecyclerView.adapter =
            MoviesAdapter(moviesList)

    }

}