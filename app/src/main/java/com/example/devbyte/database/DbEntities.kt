package com.example.devbyte.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.devbyte.domain.Video


@Entity(tableName = "dbVideo")
data class DataBaseVideo(

    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String,


    )

fun DataBaseVideo.asDomainVideoModel(): Video {
    return Video(
        url = this.url,
        updated = this.updated,
        title = this.title,
        description = this.description,
        thumbnail = this.thumbnail,
    )
}

