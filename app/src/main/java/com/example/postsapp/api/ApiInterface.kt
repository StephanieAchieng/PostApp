package com.example.postsapp.api

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}