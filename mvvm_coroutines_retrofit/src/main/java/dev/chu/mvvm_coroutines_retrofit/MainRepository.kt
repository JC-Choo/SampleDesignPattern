package dev.chu.mvvm_coroutines_retrofit

import dev.chu.mvvm_coroutines_retrofit.data.RetrofitService

class MainRepository constructor(
    private val retrofitService: RetrofitService
) {

    suspend fun getAllMovies() = retrofitService.getAllMovies()
}