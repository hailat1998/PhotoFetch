package com.hd1998.photofetch.api
import retrofit2.http.GET

const val API_KEY="3b6eNighxPn-ABVRnc7P1ZrlguRVwZiZmmfEm9mjcXU"
interface UnsplashApi {
    @GET("photos/?"+
    "client_id=$API_KEY")
    suspend fun fetchPhotos(): Photos
}