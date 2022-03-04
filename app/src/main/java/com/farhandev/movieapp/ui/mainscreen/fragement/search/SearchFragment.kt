package com.farhandev.movieapp.ui.mainscreen.fragement.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.farhandev.movieapp.databinding.FragmentSearchBinding
import com.farhandev.movieapp.service.local.MovieDb
import com.farhandev.movieapp.service.local.MovieModel
import com.farhandev.movieapp.service.network.response.ResultsItemSearchMovie
import com.farhandev.movieapp.ui.mainscreen.fragement.home.HomeViewModel
import com.farhandev.movieapp.ui.mainscreen.fragement.search.adapter.ListMovieSearchAdapter
import com.farhandev.movieapp.util.NetworkConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SearchFragment : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var adapter: ListMovieSearchAdapter
    private lateinit var db: MovieDb

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(inflater,container, false)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MovieDb(requireActivity())

        adapter = ListMovieSearchAdapter(arrayListOf(), object : ListMovieSearchAdapter.OnAdapterListener{
            override fun OnClick(movie: ResultsItemSearchMovie) {
                val movies = MovieModel(id = null,
                    backdropPath = movie.backdropPath,
                    title = movie.title,
                    rating = movie.voteAverage.toString(),true)
                db.movieDao().insertMovie(movies)
                Toast.makeText(requireActivity(), "Add to watch list success", Toast.LENGTH_SHORT).show()
            }
        })
        with(searchBinding){

            ivBtnSearch.setOnClickListener {
                var movie = etSearch.text.toString()
                when{
                    movie.isNullOrEmpty() -> {
                        Toast.makeText(requireActivity(), "Search Colum can't be null", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        progressBar.visibility = View.VISIBLE
                        searchMovie(etSearch.text.toString())
                    }
                }
            }

            rvSearchMovie.adapter = adapter
            rvSearchMovie.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rvSearchMovie.setHasFixedSize(true)
        }
    }

    private fun searchMovie(movie: String) {
        searchViewModel.getSearchMovie(movie,NetworkConfig.API_KEY)

        searchViewModel.listMovie.observe(requireActivity()) { listMovie ->
            adapter.setListMovie(listMovie)
        }

        searchViewModel.isLoading.observe(requireActivity()) { isLoading ->
            searchBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}