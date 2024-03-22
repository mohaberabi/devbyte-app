package com.example.devbyte.work

import android.content.Context
import android.net.http.HttpException
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.devbyte.database.getDataBase
import com.example.devbyte.repository.VideoRepository

class RefreshDataWorker(
    private val appContext: Context,
    private val params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {

        val database = getDataBase(appContext)
        val repository = VideoRepository(database)
        repository.refreshVideos()
        return try {
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}