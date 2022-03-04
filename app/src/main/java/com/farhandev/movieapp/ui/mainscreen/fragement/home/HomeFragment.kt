package com.farhandev.movieapp.ui.mainscreen.fragement.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhandev.movieapp.databinding.FragmentHomeBinding
import com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter.ListMovieNowPlayingAdapter
import com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter.ListMoviePopularAdapter
import com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter.ListMovieTopRatedAdapter
import com.farhandev.movieapp.ui.mainscreen.fragement.home.adapter.ListMovieUpcomingAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var adapterNowPlaying: ListMovieNowPlayingAdapter
    private lateinit var adapterPopular: ListMoviePopularAdapter
    private lateinit var adapterTopRated: ListMovieTopRatedAdapter
    private lateinit var adapterUpcoming: ListMovieUpcomingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchNowPlayingMovie()
        fetchPopularMovie()
        fetchTopRated()
        fetchUpComing()

        adapterNowPlaying = ListMovieNowPlayingAdapter(arrayListOf())
        adapterPopular = ListMoviePopularAdapter(arrayListOf())
        adapterTopRated = ListMovieTopRatedAdapter(arrayListOf())
        adapterUpcoming = ListMovieUpcomingAdapter(arrayListOf())

        with(homeBinding){
            rvNowPlaying.adapter = adapterNowPlaying
            rvNowPlaying.setHasFixedSize(true)
            rvNowPlaying.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvPopular.adapter = adapterPopular
            rvPopular.setHasFixedSize(true)
            rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvTopRated.adapter = adapterTopRated
            rvTopRated.setHasFixedSize(true)
            rvTopRated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvUpcoming.adapter = adapterUpcoming
            rvUpcoming.setHasFixedSize(true)
            rvUpcoming.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val sdf = SimpleDateFormat("EEE, dd/M/yyyy")
            val currentDate = sdf.format(Date())
            tvDate.text = currentDate
        }
    }

    private fun fetchUpComing() {
        homeViewModel.listUpComing.observe(requireActivity()) { listUpcoming ->
            adapterUpcoming.setListMovie(listUpcoming)
        }

        homeViewModel.isLoadingUpComing.observe(requireActivity()) { isLoading ->
            with(homeBinding) {
                progressUppcoming.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun fetchTopRated() {
        homeViewModel.listTopRated.observe(requireActivity()) { listTopRated ->
            adapterTopRated.setListMovie(listTopRated)
        }

        homeViewModel.isLoadingTopRated.observe(requireActivity()) { isLoading ->
            with(homeBinding) {
                progressTopRated.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun fetchPopularMovie() {
        homeViewModel.listPopular.observe(requireActivity()){ listPopular ->
            adapterPopular.setListMovie(listPopular)
        }

        homeViewModel.isLoadingPopular.observe(requireActivity()){ isLoading ->
            with(homeBinding){
                progressPopular.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun fetchNowPlayingMovie() {
        homeViewModel.listNowPlaying.observe(requireActivity()) { listNowPlaying ->
            adapterNowPlaying.setListMovie(listNowPlaying)
        }

        homeViewModel.isLoadingNowPlaying.observe(requireActivity()) { isLoading ->
            with(homeBinding) {
                progressBarNowPlaying.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}