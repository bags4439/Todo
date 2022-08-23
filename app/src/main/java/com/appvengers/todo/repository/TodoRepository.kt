package com.appvengers.todo.repository

import androidx.lifecycle.LiveData
import com.appvengers.todo.db.Todo
import com.appvengers.todo.db.TodoDao

interface TodoRepository{
    fun getAllTodos(): LiveData<List<Todo>>
    suspend fun insert(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
    suspend fun deleteAll()
}