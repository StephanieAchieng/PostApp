package com.example.postsapp.repository

import com.example.postsapp.api.ApiClient
import com.example.postsapp.api.ApiInterface

class PostRepository {
    suspend fun getPosts(): Response<List<Post>> = withContext(Dispatchers.IO) {
        val apiInterface = ApiClient.buildService(ApiInterface::class.java)
        val response = apiInterface.getPosts()
        return@withContext response
    }

    private fun withContext(io: Any, any: Any): Any {

    }
}