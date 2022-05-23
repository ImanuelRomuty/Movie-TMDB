package com.example.movietmdbchallenge.ui.detail.recomendationMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentRecomendationBinding

class RecomendationFragment : Fragment() {
    private var _binding: FragmentRecomendationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val arguments : RecomendationFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecomendationBinding.inflate(inflater, container, false)
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
            .load("https://image.tmdb.org/t/p/original/"+arguments.recommendationMovie.posterPath)
            .into(binding.movieImageView)
        binding.titleTextView.text = arguments.recommendationMovie.title
        binding.overviewTextView.text = arguments.recommendationMovie.overview
        binding.releaseDateTextView.text = arguments.recommendationMovie.releaseDate
    }
}