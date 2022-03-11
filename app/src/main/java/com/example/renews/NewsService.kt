package com.example.renews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("everything")
    fun getNewsSearch(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String
    ): Call<News>
}