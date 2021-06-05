package dev.chu.mvvm_retrofit_recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.chu.mvvm_retrofit_recyclerview.data.model.Movie
import dev.chu.mvvm_retrofit_recyclerview.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 비즈니스 로직과 API 호출 구현을 갖는 클래스.
 * data를 다루기 위한 data repository 를 constructor 에 전달한다.
 *
 * LiveData 는 Android Lifecycle을 대표하기 때문에, 이건 액티비티 또는 프레그먼트가 onStart()를 받지 않는 한 observer 호출을 호출하지 않는다.
 * 그러나. onStop()에 접근할 수 없고, LiveData는 또한 onDestroy()를 받을 때 자동으로 제거된다.
 */
class MainViewModel constructor(
    private val repository: MainRepository
) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}