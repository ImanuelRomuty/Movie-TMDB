package com.example.movietmdbchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdbchallenge.data.api.recommendationMovie.*
import com.example.movietmdbchallenge.databinding.ListRecommendationBinding
import com.example.movietmdbchallenge.ui.home.HomeFragmentDirections

class RecommendationMovieAdapter (private val item : List<Result>) : RecyclerView.Adapter<RecommendationMovieAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ListRecommendationBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ListRecommendationBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tittleTextView.text= item[position].title
        val url = "https://image.tmdb.org/t/p/original/${item[position].posterPath}"
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.posterImageView)
        holder.itemView.setOnClickListener {
            val movie = Result(
                posterPath = item[position].posterPath,
                title = item[position].title,
                overview = item[position].overview,
                releaseDate = item[position].releaseDate,
                adult = item[position].adult
            )
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRecomendationFragment(movie))
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
}