package com.example.postsapp.viewmodels

import android.app.Application
import android.content.Context

class PostsApp: Application() {
    fun OnCreate(){
        super.onCreate()
        PostsApp.appContext= applicationContext
    }
    companion object{
        lateinit var appContext: Context
    }
}