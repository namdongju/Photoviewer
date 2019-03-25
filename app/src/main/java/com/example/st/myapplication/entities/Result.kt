package com.example.st.myapplication.entities

data class Result (
        val stat: String,
        val page: Int,
        val totalPage: Int,
        val photos: ArrayList<Photo>
)