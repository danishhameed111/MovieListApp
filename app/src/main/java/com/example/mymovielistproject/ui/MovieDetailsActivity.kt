package com.example.mymovielistproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymovielistproject.adapters.MoviesAdapter
import com.example.mymovielistproject.databinding.ActivityMovieDetailsBinding
import com.example.mymovielistproject.viewmodel.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieDetailsViewModel
    lateinit var movieId: String
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        movieId = intent.getStringExtra("id").toString()

        movieViewModel.getMovieDetails(movieId).observe(this, Observer {

            Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + it.backdrop_path)
                .into(backdrop_path)

            Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + it.poster_path)
                .into(poster_path)

            binding.movieName.text = it.original_title
            binding.overviewDetail.text = it.overview
            binding.tagLineDetails.text = it.tagline
            binding.statusDetails.text = it.status
            binding.releaseDateDetails.text = it.release_date
            binding.voteAverageDetail.text = it.vote_average.toString()

        })

    }
}