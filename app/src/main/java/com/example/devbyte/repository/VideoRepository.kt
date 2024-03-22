package com.example.devbyte.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.devbyte.database.VideoDatabase
import com.example.devbyte.database.asDomainVideoModel
import com.example.devbyte.domain.Video
import com.example.devbyte.network.VideoServices
import com.example.devbyte.network.asDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(
    private val database: VideoDatabase
) {

    val videos: LiveData<List<Video>> = database.videDao.getVideos().map {
        it.map { video ->
            video.asDomainVideoModel()
        }
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = VideoServices.devBytes.getPlayList()

            for (video in playlist.asDbModel()) {
                database.videDao.insert(video)
            }
        }
    }
}