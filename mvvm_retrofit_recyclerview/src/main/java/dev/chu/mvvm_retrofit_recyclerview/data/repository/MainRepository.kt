package dev.chu.mvvm_retrofit_recyclerview.data.repository

import dev.chu.mvvm_retrofit_recyclerview.data.network.RetrofitService

/**
 * API 에서 데이터를 다루기 위한 Repository Pattern.
 * network 호출을 수행하기 위해 retrofit service 를 전달한다.
 * Repository 에서 응답을 다룰 필욘 없고, ViewModel 에서 하면 된다.
 */
class MainRepository constructor(
    private val retrofitService: RetrofitService
) {

    fun getAllMovies() = retrofitService.getAllMovies()
}