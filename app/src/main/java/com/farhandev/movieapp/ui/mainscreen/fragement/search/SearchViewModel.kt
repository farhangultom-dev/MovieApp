package com.farhandev.movieapp.ui.mainscreen.fragement.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhandev.movieapp.service.local.MovieDb
import com.farhandev.movieapp.service.local.MovieModel
import com.farhandev.movieapp.service.network.ApiConfig
import com.farhandev.movieapp.service.network.response.ResultsItemSearchMovie
import com.farhandev.movieapp.service.network.response.SarchMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _listMovie = MutableLiveData<List<ResultsItemSearchMovie>>()
    val listMovie: LiveData<List<ResultsItemSearchMovie>> = _listMovie

    fun getSearchMovie(movie: String, apikey: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchMovie(movie,apikey)
        client.enqueue(object : Callback<SarchMovieResponse> {
            override fun onResponse(
                call: Call<SarchMovieResponse>,
                response: Response<SarchMovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _listMovie.value = response.body()?.results
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SarchMovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }
}