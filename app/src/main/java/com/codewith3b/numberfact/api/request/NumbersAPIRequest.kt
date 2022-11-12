package com.codewith3b.numberfact.api.request

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersAPIRequest {

    @GET("random")
    suspend fun getRandomFact():String

    @GET("{number}")
    suspend fun getNumberFact(@Path(value = "number", encoded = true)number: Long): String

    companion object{
        private val BASE_URL = "http://numbersapi.com/"

        private var numberApi : NumbersAPIRequest? = null
        fun getInstance(): NumbersAPIRequest {
            numberApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(NumbersAPIRequest::class.java)
            return numberApi!!
        }
    }
}