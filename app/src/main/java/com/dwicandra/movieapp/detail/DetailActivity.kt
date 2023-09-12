package com.dwicandra.movieapp.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dwicandra.core.domain.model.Movie
import com.dwicandra.core.utils.images_url
import com.dwicandra.movieapp.R
import com.dwicandra.movieapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let { data ->
            binding.content.tvItemTitleMovie.text = detailMovie.title
            val poster = images_url + detailMovie.posterPath
            Glide.with(this)
                .load(poster)
                .into(binding.content.ivItemDetailImage)
            binding.content.tvItemDetailRatingMovie.text = detailMovie.voteCount.toString()
            binding.content.tvItemDetailOverviewMovie.text = detailMovie.overview

            var isFavorite = detailMovie.isFavorite
            setMovieFavorite(isFavorite)
            binding.content.fabItemDetail.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(detailMovie, isFavorite)
                setMovieFavorite(isFavorite)
            }
        }
    }

    private fun setMovieFavorite(state: Boolean) {
        binding.content.fabItemDetail.apply {
            if (state) setImageResource(R.drawable.ic_outline_favorite_red_24) else setImageResource(
                R.drawable.ic_baseline_favorite_border_24
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}