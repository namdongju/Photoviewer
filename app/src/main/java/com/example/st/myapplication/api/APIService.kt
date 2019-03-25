package com.example.st.myapplication.api

import io.reactivex.Single
import com.example.st.myapplication.entities.Result

import retrofit2.http.GET

interface APIService{
    @GET("/images")
    fun getPhotos(): Single<Result>
}