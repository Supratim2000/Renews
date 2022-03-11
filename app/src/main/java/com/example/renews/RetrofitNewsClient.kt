package com.example.renews

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNewsClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL: String = "https://newsapi.org/v2/"

    fun getRetrofitClient(): Retrofit? {
        if (retrofit == null) {
            val gson: GsonConverterFactory = GsonConverterFactory.create()
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(gson).build()
        }
        return retrofit
    }
}