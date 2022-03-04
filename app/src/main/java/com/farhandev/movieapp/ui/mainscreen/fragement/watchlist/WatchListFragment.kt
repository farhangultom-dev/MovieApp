package com.farhandev.movieapp.ui.mainscreen.fragement.watchlist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhandev.movieapp.databinding.FragmentWatchListBinding
import com.farhandev.movieapp.service.local.MovieDb
import com.farhandev.movieapp.service.local.MovieModel


class WatchListFragment : Fragment() {

    private lateinit var watcgListBinding: FragmentWatchListBinding
    private lateinit var db: MovieDb
    private lateinit var adapter: ListMovieWatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watcgListBinding = FragmentWatchListBinding.inflate(inflater,container,false)
        return watcgListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MovieDb(requireActivity())

        adapter = ListMovieWatchListAdapter(arrayListOf(), object : ListMovieWatchListAdapter.onAdapterWatchLister{
            override fun OnClick(movie: MovieModel) {
                db.movieDao().deleteById(movie.id!!)
            }

        })

        initData()


        with(watcgListBinding){
            rvWatchList.adapter = adapter
            rvWatchList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            rvWatchList.setHasFixedSize(true)
        }

    }

    private fun initData() {
        adapter.setListMovie(db.movieDao().getAllMovie())
    }
}