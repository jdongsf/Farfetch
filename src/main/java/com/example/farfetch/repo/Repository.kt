package com.example.farfetch.repo

import com.example.farfetch.Appconstants.BASE_URL

object Repository {
    val fetchAPI: FetchApi by lazy { RetrofitFactory.retrofit(BASE_URL).create(FetchApi::class.java) }
}