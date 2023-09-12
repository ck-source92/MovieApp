package com.dwicandra.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwicandra.core.R
import com.dwicandra.core.databinding.ItemListMovieBinding
import com.dwicandra.core.domain.model.Movie
import com.dwicandra.core.utils.images_url

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    val listMovie = ArrayList<Movie>()
    var onClickListener: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(movieList: List<Movie>?) {
        if (movieList == null) return
        listMovie.clear()
        listMovie.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            val poster = images_url + data.posterPath
            with(binding) {
                Glide.with(itemView.context)
                    .load(poster)
                    .error(R.mipmap.ic_launcher)
                    .into(ivItemImage)
                tvTitle.text = data.title ?: ""
            }
        }

        init {
            binding.root.setOnClickListener {
                onClickListener?.invoke(listMovie[bindingAdapterPosition])
            }
        }
    }
}