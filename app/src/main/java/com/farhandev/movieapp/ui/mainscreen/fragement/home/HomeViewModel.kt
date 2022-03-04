package com.farhandev.movieapp.ui.mainscreen.fragement.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhandev.movieapp.service.network.ApiConfig
import com.farhandev.movieapp.service.network.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _isLoadingNowPlaying = MutableLiveData<Boolean>()
    val isLoadingNowPlaying: MutableLiveData<Boolean> = _isLoadingNowPlaying

    private val _listNowPlaying = MutableLiveData<List<ResultsItem>>()
    val listNowPlaying: LiveData<List<ResultsItem>> = _listNowPlaying

    private val _isLoadingPopular = MutableLiveData<Boolean>()
    val isLoadingPopular: MutableLiveData<Boolean> = _isLoadingNowPlaying

    private val _listPopular = MutableLiveData<List<ResultsItemPopular>>()
    val listPopular: LiveData<List<ResultsItemPopular>> = _listPopular

    private val _isLoadingTopRated = MutableLiveData<Boolean>()
    val isLoadingTopRated: MutableLiveData<Boolean> = _isLoadingTopRated

    private val _listTopRated = MutableLiveData<List<ResultsItemTopRated>>()
    val listTopRated: LiveData<List<ResultsItemTopRated>> = _listTopRated

    private val _isLoadingUpComing = MutableLiveData<Boolean>()
    val isLoadingUpComing: MutableLiveData<Boolean> = _isLoadingUpComing

    private val _listUpComing = MutableLiveData<List<ResultsItemUpcoming>>()
    val listUpComing: LiveData<List<ResultsItemUpcoming>> = _listUpComing

    init {
        getNowPlaying()
        getPopular()
        getTopRated()
        getUpComing()
    }

    private fun getUpComing() {
        _isLoadingUpComing.value = true
        val client = ApiConfig.getApiService().getUpcomingMovie()
        client.enqueue(object : Callback<UpComingResponse>{
            override fun onResponse(
                call: Call<UpComingResponse>,
                response: Response<UpComingResponse>
            ) {
                _isLoadingUpComing.value = false
                if (response.isSuccessful){
                    _listUpComing.value = response.body()?.results
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
                _isLoadingUpComing.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }

    private fun getTopRated() {
        _isLoadingTopRated.value = true
        val client = ApiConfig.getApiService().getTopRatedovie()
        client.enqueue(object : Callback<TopRatedResponse>{
            override fun onResponse(
                call: Call<TopRatedResponse>,
                response: Response<TopRatedResponse>
            ) {
                _isLoadingTopRated.value = false
                if (response.isSuccessful){
                    _listTopRated.value = response.body()?.results
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                _isLoadingTopRated.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }

    private fun getPopular() {
        _isLoadingPopular.value = true
        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<PopularResponse>{
            override fun onResponse(
                call: Call<PopularResponse>,
                response: Response<PopularResponse>
            ) {
                _isLoadingPopular.value = false
                if (response.isSuccessful){
                    _listPopular.value = response.body()?.results
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                _isLoadingPopular.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }

    private fun getNowPlaying(){
        _isLoadingNowPlaying.value = true
        val client = ApiConfig.getApiService().getMovieNowPlaying()
        client.enqueue(object : Callback<NowPlayingResponse>{
            override fun onResponse(
                call: Call<NowPlayingResponse>,
                response: Response<NowPlayingResponse>
            ) {
                _isLoadingNowPlaying.value = false
                if (response.isSuccessful){
                    _listNowPlaying.value = response.body()?.results
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                _isLoadingNowPlaying.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }
}