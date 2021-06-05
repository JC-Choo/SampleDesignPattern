package dev.chu.mvvm_coroutines_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import dev.chu.mvvm_coroutines_retrofit.data.Api
import dev.chu.mvvm_coroutines_retrofit.databinding.ActivityMainBinding
import dev.chu.mvvm_coroutines_retrofit.etc.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val retrofitService = Api.createApi()
        val mainRepository = MainRepository(retrofitService)
        ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)
    }
    private val adapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this, {
            adapter.setMovies(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, {
            binding.progressDialog.isVisible = it
        })

        viewModel.getAllMovies()
    }
}