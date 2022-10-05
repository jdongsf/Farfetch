package com.example.farfetch.repo

import com.example.farfetch.entity.Hire
import retrofit2.http.GET

interface FetchApi {
    @GET("hiring.json")
    suspend fun getItems(): List<Hire>
}