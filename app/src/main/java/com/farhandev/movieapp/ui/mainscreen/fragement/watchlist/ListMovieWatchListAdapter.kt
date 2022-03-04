package com.farhandev.movieapp.ui.mainscreen.fragement.watchlist

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhandev.movieapp.R
import com.farhandev.movieapp.databinding.SearchItemBinding
import com.farhandev.movieapp.service.local.MovieModel
import com.farhandev.movieapp.ui.mainscreen.fragement.search.adapter.ListMovieSearchAdapter
import com.farhandev.movieapp.util.NetworkConfig

class ListMovieWatchListAdapter(val listWatchListMovie: ArrayList<MovieModel>, val listener: onAdapterWatchLister)
    : RecyclerView.Adapter<ListMovieWatchListAdapter.ViewHolder>(){

    fun setListMovie(movie: List<MovieModel>){
        listWatchListMovie.clear()
        listWatchListMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMovieWatchListAdapter.ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListMovieWatchListAdapter.ViewHolder, position: Int) {
        holder.bind(listWatchListMovie[position])
    }

    override fun getItemCount(): Int = listWatchListMovie.size

    inner class ViewHolder(val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie : MovieModel){
            with(binding){
                Glide.with(itemView.context)
                    .load("${NetworkConfig.BASE_IMAGE_URL}${movie.backdropPath}")
                    .into(binding.ivMovies)

                tvMoviesTitle.text = movie.title
                tvRating.text = movie.rating

                if (movie.bookmarked){
                    ivBookmark.setImageResource(R.drawable.ic_bookmarked)
                }

                ivBookmark.setOnClickListener {
                    listener.OnClick(movie)
                    ivBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }

    interface onAdapterWatchLister{
        fun OnClick(movie: MovieModel)
    }
}