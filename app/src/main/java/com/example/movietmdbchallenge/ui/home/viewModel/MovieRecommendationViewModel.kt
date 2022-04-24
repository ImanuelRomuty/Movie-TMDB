package com.example.movietmdbchallenge.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.recommendationMovie.Result
import com.example.movietmdbchallenge.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MovieRecommendationViewModel : ViewModel() {
    private val  movieRecommendation : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMoviesRecommendation()
        }
    }
    fun getMovieRecommendation():LiveData<List<Result>>{
        return movieRecommendation
    }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}


    private fun getAllMoviesRecommendation() {
        MovieApi.instance.allMovieRecommendation().enqueue(object : Callback<RecommendationMovieResponse> {
            override fun onResponse(call: Call<RecommendationMovieResponse>, response: Response<RecommendationMovieResponse>) {
                movieRecommendation.value = response.body()?.results
                Log.d("MOVIEREQ", movieRecommendation.value.toString())
            }
            override fun onFailure(call: Call<RecommendationMovieResponse>, t: Throwable) {
                Log.d("CAKEP",t.message.toString())
            }
        })
    }
}