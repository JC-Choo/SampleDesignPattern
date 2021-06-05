package dev.chu.mvvm_coroutines_retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.chu.mvvm_coroutines_retrofit.data.model.Movie
import kotlinx.coroutines.*

class MainViewModel constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    /**
     * LiveData 는 UI에 대한 관찰 가능한 값 홀더이며 메인 쓰레드에서 값에 접근할 수 있다.
     */
    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}