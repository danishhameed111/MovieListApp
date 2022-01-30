package com.example.mymovielistproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mymovielistproject.model.MovieResponseDetails
import com.example.mymovielistproject.repositry.RepositoryMovieDetailsData

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

    fun getMovieDetails(movieId : String) : LiveData<MovieResponseDetails> {
        return RepositoryMovieDetailsData.getMovieDetail(movieId)
    }
}