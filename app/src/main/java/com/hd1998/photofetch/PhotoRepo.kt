package com.hd1998.photofetch

import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.api.Photos
import com.hd1998.photofetch.api.UnsplashApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepo {
    private val unsplashApi : UnsplashApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        unsplashApi = retrofit.create()
    }
    suspend fun photoFetch() : Photos = unsplashApi.fetchPhotos()
}