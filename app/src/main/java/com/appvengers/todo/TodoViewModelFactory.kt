package com.appvengers.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appvengers.todo.repository.TodoRepository

class TodoViewModelFactory(
    private val repository: TodoRepository
) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(repository) as T
        }

        throw  IllegalArgumentException("Unknown View Model class")
    }
}