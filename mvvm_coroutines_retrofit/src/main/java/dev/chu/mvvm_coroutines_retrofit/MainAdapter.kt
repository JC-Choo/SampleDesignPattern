package dev.chu.mvvm_coroutines_retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.chu.mvvm_coroutines_retrofit.data.model.Movie
import dev.chu.mvvm_coroutines_retrofit.databinding.ItemMainBinding

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var movieList = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(
    val binding: ItemMainBinding
) : RecyclerView.ViewHolder(binding.root)