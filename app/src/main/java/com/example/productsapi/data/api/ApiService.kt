package com.example.productsapi.data.api

import com.example.productsapi.data.model.CategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//create api service
private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

//Why?: Using lazy ensures that the Retrofit instance is created only when needed,
// avoiding static initialization issues.
val service: ApiService by lazy {
    getRetrofit().create(ApiService::class.java)
}

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories() : CategoryResponse
}