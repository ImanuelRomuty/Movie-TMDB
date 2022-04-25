package com.example.movietmdbchallenge.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.adapter.RecommendationMovieAdapter
import com.example.movietmdbchallenge.adapter.UpComingMovieAdapter
import com.example.movietmdbchallenge.databinding.FragmentHomeBinding
import com.example.movietmdbchallenge.ui.home.viewModel.MovieRecommendationViewModel
import com.example.movietmdbchallenge.ui.home.viewModel.MovieUpComingViewModel
import com.example.movietmdbchallenge.ui.login.LoginViewModel
import com.example.movietmdbchallenge.ui.splashScreen.SplashViewModel


class HomeFragment : Fragment() {
    private val viewModelRecommendation :   MovieRecommendationViewModel by activityViewModels()
    private val viewModelUpComing :         MovieUpComingViewModel by activityViewModels()
    private val viewModelUser :             UserViewModel by activityViewModels()

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
        fetchMovieUpComing()
        viewModelUser.getUserData()
        binding.imageView.setOnClickListener {
            logOut()
        }
        binding.userImageView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileUserFragment())
        }
    }
    private fun catchUsername(){
        viewModelUser.usernamePatch.observe(viewLifecycleOwner){
            Log.d("CAPEK","FAIL")
            binding.usernameTextView.text = it.toString()
            Log.d("USERAMEUSER",it.toString())
        }
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
    private fun logOut(){
//        viewModelLogin.logout()
//        viewModelLogin.getcekValidLogOut().observe(viewLifecycleOwner){
//            if(it==0){
//                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
//            }
//        }
    }
}