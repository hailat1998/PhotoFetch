package com.hd1998.photofetch.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
 private const val API_KEY="3b6eNighxPn-ABVRnc7P1ZrlguRVwZiZmmfEm9mjcXU"
class PhotoInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
     val originalRequest : Request = chain.request()
        val newURL : HttpUrl= originalRequest.url.newBuilder()
            .addQueryParameter("client_id" , API_KEY)
            .addQueryParameter("page" , "2")
            .build()
        val newRequest : Request = originalRequest.newBuilder()
            .url(newURL)
            .build()
        return chain.proceed(newRequest)
    }
}