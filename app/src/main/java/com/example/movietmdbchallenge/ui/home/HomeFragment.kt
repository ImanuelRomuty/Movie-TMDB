package com.example.movietmdbchallenge.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdbchallenge.MyApplication
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.adapter.RecommendationMovieAdapter
import com.example.movietmdbchallenge.adapter.UpComingMovieAdapter
import com.example.movietmdbchallenge.databinding.FragmentHomeBinding

import com.example.movietmdbchallenge.ui.home.viewModel.MovieRecommendationViewModel

import com.example.movietmdbchallenge.ui.login.LoginViewModel
import com.example.movietmdbchallenge.ui.splashScreen.SplashViewModel
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel : HomeViewModel by viewModel()
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catchUsername()
        fetchMovieRecommendation()
        binding.userImageView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileUserFragment())
        }
    }
    private fun catchUsername(){
        viewModel.getUsername().observe(viewLifecycleOwner){
            binding.usernameTextView.text = it.toString()
        }
    }

    private fun fetchMovieRecommendation(){
        viewModel.getMovieRecommendation().observe(viewLifecycleOwner){
            Log.d("CEKOBSERV",it.toString())
            val adapter = RecommendationMovieAdapter(it)
            val layoutManager =  GridLayoutManager(requireContext(),
                2)
            binding.recommendationMovieRecyclerView.layoutManager = layoutManager
            binding.recommendationMovieRecyclerView.adapter = adapter
        }
    }
}