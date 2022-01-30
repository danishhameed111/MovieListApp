package com.example.mymovielistproject.repositry

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mymovielistproject.model.APIClient
import com.example.mymovielistproject.model.ApiInterface
import com.example.mymovielistproject.model.MovieResponseDetails
import com.example.mymovielistproject.utils.AppConstants
import retrofit2.Call
import retrofit2.Response

object RepositoryMovieDetailsData {

    private val apiInterface = APIClient().getRetrofit()!!.create(ApiInterface::class.java)

    val movieDetails : MutableLiveData<MovieResponseDetails> by lazy { MutableLiveData<MovieResponseDetails>() }

    fun getMovieDetail(movieId : String) : MutableLiveData<MovieResponseDetails> {

        val call = apiInterface.getMovieDetails(movieId, AppConstants.API_KEY)

        call.enqueue(object : retrofit2.Callback<MovieResponseDetails> {

            override fun onResponse(
                call: Call<MovieResponseDetails>, response: Response<MovieResponseDetails>) {

                if(response.isSuccessful){

                    movieDetails.postValue(response.body()!!)

                }
            }

            override fun onFailure(call: Call<MovieResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message.toString())
            }

        })
        return movieDetails
    }
}