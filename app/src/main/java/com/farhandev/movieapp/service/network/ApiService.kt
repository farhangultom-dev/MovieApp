package com.farhandev.movieapp.service.network

import com.farhandev.movieapp.service.network.response.*
import com.farhandev.movieapp.util.NetworkConfig.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?api_key=$API_KEY")
    fun getMovieNowPlaying(): Call<NowPlayingResponse>

    @GET("movie/popular?api_key=$API_KEY")
    fun getPopularMovie(): Call<PopularResponse>

    @GET("movie/top_rated?api_key=$API_KEY")
    fun getTopRatedovie(): Call<TopRatedResponse>

    @GET("movie/upcoming?api_key=$API_KEY")
    fun getUpcomingMovie(): Call<UpComingResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): Call<SarchMovieResponse>
}