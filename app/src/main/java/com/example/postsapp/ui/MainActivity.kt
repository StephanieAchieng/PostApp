package com.example.postsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.R
import com.example.postsapp.repository.PostRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postsRepository= PostRepository()
        postsViewModelFactory= PostsViewModelFactory(postsRepository)
        postsViewModel=
                ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)

        postsViewModel.getPosts()

        postsViewModel.postsLiveData.observe(this, Observer { postsList->
            recycler_post.apply{
                layoutManager=LinearLayoutManager(this@MainActivity)
                hasFixedSize()
                adapter=PostsAdapter(postsList)
            }
            Toast.makeText(baseContext,"${postsList.size} posts fetched",Toast.LENGTH_LONG).show()
        })
        postsViewModel.postsFailedLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }
}