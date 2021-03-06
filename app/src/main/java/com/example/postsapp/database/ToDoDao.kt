package com.example.postsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postsapp.models.ToDo

interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDo(todo: ToDo)

    @Query("SELECT * FROM todos")
    fun getComments(): LiveDataScope<List<ToDo>>

    @Query("SELECT * FROM todos WHERE id = :postId")
    fun getPostById(postId:Int): LiveData<ToDo>
}