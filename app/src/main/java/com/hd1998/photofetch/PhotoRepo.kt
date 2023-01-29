package com.hd1998.photofetch

import com.hd1998.photofetch.api.PhotoInterceptor
import com.hd1998.photofetch.api.PhotoItem
import com.hd1998.photofetch.api.Photos
import com.hd1998.photofetch.api.UnsplashApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepo {
    private val unsplashApi : UnsplashApi
    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoInterceptor())
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        unsplashApi = retrofit.create()
    }
    suspend fun photoFetch() : List<PhotoItem> = unsplashApi.fetchPhotos()
    suspend fun photoSearch(query : String) : List<PhotoItem> =  unsplashApi.searchPhoto(query).photoItems
}