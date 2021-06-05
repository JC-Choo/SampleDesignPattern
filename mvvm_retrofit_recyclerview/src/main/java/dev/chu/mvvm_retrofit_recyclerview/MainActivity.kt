package dev.chu.mvvm_retrofit_recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.chu.mvvm_retrofit_recyclerview.data.network.Api
import dev.chu.mvvm_retrofit_recyclerview.data.repository.MainRepository
import dev.chu.mvvm_retrofit_recyclerview.databinding.ActivityMainBinding
import dev.chu.mvvm_retrofit_recyclerview.etc.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        val retrofitService = Api.createApi()
        ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this, {
            Log.d(TAG, "list : $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(this, {
            Log.e(TAG, "error message : $it")
        })
        viewModel.getAllMovies()
    }
}