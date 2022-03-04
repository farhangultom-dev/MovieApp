package com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhandev.movieapp.databinding.PopularItemBinding
import com.farhandev.movieapp.service.network.response.ResultsItemUpcoming
import com.farhandev.movieapp.util.NetworkConfig

class ListMovieUpcomingAdapter(val listUpComingdMovie: ArrayList<ResultsItemUpcoming>)
    : RecyclerView.Adapter<ListMovieUpcomingAdapter.ViewHolder>(){

    fun setListMovie(movie: List<ResultsItemUpcoming>){
        listUpComingdMovie.clear()
        listUpComingdMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMovieUpcomingAdapter.ViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListMovieUpcomingAdapter.ViewHolder, position: Int) {
        holder.bind(listUpComingdMovie[position])
    }

    override fun getItemCount(): Int = listUpComingdMovie.size

    inner class ViewHolder(val binding: PopularItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie : ResultsItemUpcoming){
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