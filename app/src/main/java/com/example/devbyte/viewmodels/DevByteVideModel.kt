package com.example.devbyte.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.devbyte.database.getDataBase
import com.example.devbyte.domain.Video
import com.example.devbyte.repository.VideoRepository
import kotlinx.coroutines.launch

class DevByteVideModel(app: Application) : AndroidViewModel(app) {


    private val database = getDataBase(app)
    private val repository = VideoRepository(database)

    init {


        viewModelScope.launch {
            repository.refreshVideos()
        }
    }

    val playlist = repository.videos

    class Factory(private val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteVideModel::class.java)) {
                return DevByteVideModel(app) as T
            }
            throw IllegalArgumentException("ERROR")
        }
    }
}