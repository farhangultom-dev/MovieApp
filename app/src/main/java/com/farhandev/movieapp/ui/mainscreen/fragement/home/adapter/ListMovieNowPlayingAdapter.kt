package com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhandev.movieapp.databinding.NowPlayingItemBinding
import com.farhandev.movieapp.service.network.response.ResultsItem
import com.farhandev.movieapp.util.NetworkConfig

class ListMovieNowPlayingAdapter(val listMovieNowPlaying: ArrayList<ResultsItem>)
    : RecyclerView.Adapter<ListMovieNowPlayingAdapter.ViewHolder>(){

    fun setListMovie(movie: List<ResultsItem>){
        listMovieNowPlaying.clear()
        listMovieNowPlaying.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMovieNowPlayingAdapter.ViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListMovieNowPlayingAdapter.ViewHolder, position: Int) {
        holder.bind(listMovieNowPlaying[position])
    }

    override fun getItemCount(): Int = listMovieNowPlaying.size

    inner class ViewHolder(val binding: NowPlayingItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie : ResultsItem){
            with(binding){
                Glide.with(itemView.context)
                    .load("${NetworkConfig.BASE_IMAGE_URL}${movie.backdropPath}")
                    .into(binding.ivMovies)

                tvMoviesTitle.text = movie.title
                tvRating.text = movie.voteAverage.toString()
            }
        }
    }
}