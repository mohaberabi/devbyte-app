package com.example.devbyte.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


@Dao
interface VideoDao {
    @Query("select * from dbVideo")

    fun getVideos(): LiveData<List<DataBaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: DataBaseVideo)


}


@Database(entities = [DataBaseVideo::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {

    abstract val videDao: VideoDao
}


private lateinit var INSTANCE: VideoDatabase


fun getDataBase(context: Context): VideoDatabase {
    if (!::INSTANCE.isInitialized) {
        INSTANCE =
            Room.databaseBuilder(
                context.applicationContext,
                VideoDatabase::class.java,
                "videoDb"
            )
                .build()
    }

    return INSTANCE
}