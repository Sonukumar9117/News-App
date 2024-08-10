package com.example.headlinehub.models

import retrofit2.http.GET

interface GetData {
    @GET("everything?q=bitcoin&apiKey=0e8d2650a5c849f18c89accb30adb06f")
   fun getData():retrofit2.Call<NewsData>
}