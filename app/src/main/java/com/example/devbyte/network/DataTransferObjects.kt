package com.example.devbyte.network

import com.example.devbyte.database.DataBaseVideo
import com.example.devbyte.database.VideoDatabase
import com.example.devbyte.domain.Video
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)


data class NetworkVideoContainer(val videos: List<NetworkVideo>)


@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)


fun NetworkVideoContainer.asDomainModel(): List<Video> {

    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

fun NetworkVideoContainer.asDbModel(): List<DataBaseVideo> {

    return videos.map {
        DataBaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}