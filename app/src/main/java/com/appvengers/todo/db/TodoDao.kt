package com.appvengers.todo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao()
interface TodoDao{

    @Query(value = "INSERT INTO Todo (task, description) VALUES (:task,:desc)")
    suspend fun insertTodo(task: String?, desc: String?)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query(value = "DELETE FROM Todo")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM Todo")
    fun getAllTodos(): LiveData<List<Todo>>
}