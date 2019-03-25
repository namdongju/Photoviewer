package com.example.st.myapplication.api

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class APIClient {

    private var retrofit: Retrofit? = null


    constructor(){
        this.retrofit = Retrofit.Builder()
                .baseUrl("http://demo2587971.mockable.io")
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun getAPIService(): APIService {
        return retrofit!!.create(APIService::class.java)
    }
}