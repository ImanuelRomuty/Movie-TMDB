package com.example.movietmdbchallenge.ui.detail.upComingMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentUpComingMovieBinding
import com.example.movietmdbchallenge.ui.detail.recomendationMovie.RecomendationFragmentArgs



class upComingMovieFragment : Fragment() {
    private var _binding: FragmentUpComingMovieBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val arguments : upComingMovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpComingMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/"+arguments.upComingMovie.posterPath)
            .into(binding.movieImageView)
        binding.titleTextView.text = arguments.upComingMovie.title
        binding.overviewTextView.text = arguments.upComingMovie.overview
        binding.releaseDateTextView.text = arguments.upComingMovie.releaseDate
    }

}