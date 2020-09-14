package com.example.postsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.repository.PostRepository

class PostViewModel(val postRepository: PostRepository) : ViewModel(){
        var postsLiveData=MutableLiveData<List<Post>>()
        var postsFailedLiveData= MutableLiveData<String>()
        fun getPosts(){
            viewModelScope.launch{
                val response =postRepository.getPost()
                if (response.isSuccessful){
                    postsLiveData.postValue(response.body())

                }
                else{
                    postsFailedLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
}