package dev.chu.mvvm_coroutines_retrofit.etc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.chu.mvvm_coroutines_retrofit.MainRepository
import dev.chu.mvvm_coroutines_retrofit.MainViewModel

class MyViewModelFactory constructor(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}