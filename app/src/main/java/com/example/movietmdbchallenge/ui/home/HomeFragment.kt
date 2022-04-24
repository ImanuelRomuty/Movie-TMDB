package com.example.movietmdbchallenge.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.adapter.RecommendationMovieAdapter
import com.example.movietmdbchallenge.adapter.UpComingMovieAdapter
import com.example.movietmdbchallenge.databinding.FragmentHomeBinding
import com.example.movietmdbchallenge.ui.home.viewModel.MovieRecommendationViewModel
import com.example.movietmdbchallenge.ui.home.viewModel.MovieUpComingViewModel


class HomeFragment : Fragment() {
    private val viewModelRecommendation : MovieRecommendationViewModel by viewModels()
    private val viewModelUpComing : MovieUpComingViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovieRecommendation()
        fetchMovieUpComing()
    }

    private fun fetchMovieRecommendation(){
        viewModelRecommendation.getMovieRecommendation().observe(viewLifecycleOwner){
            val adapter = RecommendationMovieAdapter(it)
            val layoutManager =  LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            binding.recommendationMovieRecyclerView.layoutManager = layoutManager
            binding.recommendationMovieRecyclerView.adapter = adapter
        }
    }
    private fun fetchMovieUpComing(){
        viewModelUpComing.getMovieUpComing().observe(viewLifecycleOwner){
            val adapter = UpComingMovieAdapter(it)
            val layoutManager =  LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            binding.upcomingMovieRecyclerView.layoutManager = layoutManager
            binding.upcomingMovieRecyclerView.adapter = adapter
        }
    }

}