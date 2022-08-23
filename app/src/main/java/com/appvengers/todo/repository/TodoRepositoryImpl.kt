package com.appvengers.todo.repository

import androidx.lifecycle.LiveData
import com.appvengers.todo.db.Todo
import com.appvengers.todo.db.TodoDao
import com.appvengers.todo.db.TodoDatabase

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository{

    override fun getAllTodos(): LiveData<List<Todo>> {
        return dao.getAllTodos()
    }

    override suspend fun insert(todo: Todo) {
        dao.insertTodo(todo.task!!,todo.description!!)
    }

    override suspend fun update(todo: Todo) {
        dao.updateTodo(todo)
    }

    override suspend fun delete(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}