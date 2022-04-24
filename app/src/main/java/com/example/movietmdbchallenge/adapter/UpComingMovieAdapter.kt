package com.example.movietmdbchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdbchallenge.data.api.upComingMovie.Result
import com.example.movietmdbchallenge.databinding.ListRecommendationBinding
import com.example.movietmdbchallenge.databinding.ListUpComingBinding
import com.example.movietmdbchallenge.ui.home.HomeFragmentDirections

class UpComingMovieAdapter (private val item : List<Result>) : RecyclerView.Adapter<UpComingMovieAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ListUpComingBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ListUpComingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUpComingMovieFragment(movie))
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
}