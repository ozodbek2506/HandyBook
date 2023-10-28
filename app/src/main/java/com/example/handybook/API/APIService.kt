package com.example.handybook.API

import com.example.handybook.Data.Books
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("/book-api")
    fun getAllBooks(): Call<Books>

    @GET("book-api/all-category")
    fun getAllCategories(): Call<List<String>>
}