package com.example.devbyte.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DevByteServices {


    @GET("devbytes.json")

    suspend fun getPlayList(): NetworkVideoContainer
}


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


object VideoServices {
    private val retroFit = Retrofit.Builder().baseUrl("https://devbytes.udacity.com/")
        .addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).build()

    val devBytes = retroFit.create(DevByteServices::class.java)
}