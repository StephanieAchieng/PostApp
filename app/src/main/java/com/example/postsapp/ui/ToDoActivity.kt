package com.example.postsapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.R
import com.example.postsapp.repository.ToDosRepository
import com.example.postsapp.viewmodels.ToDosViewModel
import com.example.postsapp.viewmodels.ToDosViewModelFactory

class ToDoActivity : AppCompatActivity() {
    lateinit var toDosViewModel: ToDosViewModel
    lateinit var toDosViewModelFactory: ToDosViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        val toDosRepository= ToDosRepository()
        toDosViewModelFactory= ToDosViewModelFactory(toDosRepository)
        toDosViewModel=
            ViewModelProvider(this, toDosViewModelFactory).get(ToDosViewModel::class.java)

        toDosViewModel.getToDos()

        toDosViewModel.todosLiveData.observe(this, Observer { todosList->
            rvToDo.apply{
                layoutManager= LinearLayoutManager(this@ToDoActivity)
                hasFixedSize()
                adapter=ToDoAdapter(todosList)
            }
            Toast.makeText(baseContext,"${todosList.size} comments fetched", Toast.LENGTH_LONG).show()
        })
        toDosViewModel.todosFailedLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error, Toast.LENGTH_LONG).show()
        })
    }
}