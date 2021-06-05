package dev.chu.mvvm_coroutines_retrofit.data

import dev.chu.mvvm_coroutines_retrofit.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>
}