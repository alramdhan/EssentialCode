package com.logixphere.essentialcode.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logixphere.essentialcode.data.models.BaseResponse
import com.logixphere.essentialcode.data.models.movies.MovieResponse
import com.logixphere.essentialcode.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val _dataMovie = MutableLiveData<BaseResponse<List<MovieResponse>>>()
    val dataMovie: LiveData<BaseResponse<List<MovieResponse>>> = _dataMovie
    private val _loading = MutableLiveData(true)
    val showLoading: LiveData<Boolean> = _loading
    private val _navigationSelectedItem = MutableLiveData(0)
    val navSelectedItem: LiveData<Int> = _navigationSelectedItem

    fun getData() = viewModelScope.launch {
        _loading.postValue(true)
        try {
            val response = movieRepository.getAll()
            Log.d("MovieViewModel", "masuk asu")
            Log.d("MovieViewModel", response.body()!!.toString())
            if(response.isSuccessful) {
                _dataMovie.postValue(BaseResponse.Success(response.body()))
            } else {
                _dataMovie.postValue(BaseResponse.Failure(response.code(), response.message()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _dataMovie.postValue(BaseResponse.Failure(500, e.message!!))
        }
        _loading.postValue(false)
    }
}