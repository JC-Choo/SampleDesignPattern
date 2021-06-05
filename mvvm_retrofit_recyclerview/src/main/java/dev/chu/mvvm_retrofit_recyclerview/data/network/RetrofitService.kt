package dev.chu.mvvm_retrofit_recyclerview.data.network

import dev.chu.mvvm_retrofit_recyclerview.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("movielist.json")
    fun getAllMovies(): Call<List<Movie>>
}