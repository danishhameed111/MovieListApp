package com.example.mymovielistproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mymovielistproject.model.Movies
import com.example.mymovielistproject.repositry.RepositoryData

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    init {
        RepositoryData.initDatabase(application)
    }

    fun getMovies(type : String, tBool :Int) : LiveData<List<Movies>>{
        return RepositoryData.getMovies(type, tBool)
    }

}