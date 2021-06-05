package dev.chu.mvvm_retrofit_recyclerview.etc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.chu.mvvm_retrofit_recyclerview.MainViewModel
import dev.chu.mvvm_retrofit_recyclerview.data.repository.MainRepository

/**
 * ViewModel만으론 ViewModel을 만들 수 없다. ViewModels를 생성하기 위해 안드로이드에 의해 제공된 ViewModelProviders 유틸리티가 필요하다.
 * ViewModelProviders 는 인자 생성자 없이 ViewModels 를 인스턴스화 시킬 수 있다.
 * 여러 인자가 필요한 ViewModel을 갖고 싶다면, MainViewModel 인스턴스가 요구할 때 ViewModelProviders가 필요한 것을 전달할 수 있는 Factory를 사용해야만 한다.
 */
class ViewModelFactory constructor(
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