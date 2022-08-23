package com.appvengers.todo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appvengers.todo.db.Todo
import com.appvengers.todo.repository.TodoRepository
import com.appvengers.todo.repository.TodoRepositoryImpl
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
): ViewModel(), Observable{

    private var isUpdateOrDelete = false
    private lateinit var todoToUpdateOrDelete: Todo
    private var message: MutableLiveData<String> = MutableLiveData()

    @Bindable
    val tvTaskName = MutableLiveData<String?>()

    @Bindable
    val tvTaskDescription = MutableLiveData<String?>()

    @Bindable
    val btnSaveOrUpdate = MutableLiveData<String>()

    @Bindable
    val btnDeleteOrClearAll = MutableLiveData<String>()


    init {
        btnSaveOrUpdate.value = "Save"
        btnDeleteOrClearAll.value = "Clear All"
    }


    fun getNotificationMessage():LiveData<String>{
        return message
    }

    fun saveOrUpdate(){
        val taskName = tvTaskName.value!!
        val taskDesc = tvTaskDescription.value!!

        if(isUpdateOrDelete){
            todoToUpdateOrDelete.task = taskName
            todoToUpdateOrDelete.description = taskDesc
            update(todoToUpdateOrDelete)
            isUpdateOrDelete =false
            btnSaveOrUpdate.value = "Save"
            btnDeleteOrClearAll.value = "Clear All"
        }
        else {
            insert(Todo(0, taskName, taskDesc))
        }

        tvTaskName.value = null
        tvTaskDescription.value = null
    }

    fun deleteOrClearAll(){
        if(isUpdateOrDelete){
            delete(todoToUpdateOrDelete)
            tvTaskName.value = null
            tvTaskDescription.value = null
            isUpdateOrDelete =false
            btnSaveOrUpdate.value = "Save"
            btnDeleteOrClearAll.value = "Clear All"
        }
        else {
            clearAll()
        }
    }

    val todos = repository.getAllTodos()

    fun insert(todo: Todo){
        viewModelScope.launch {
            repository.insert(todo)

            message.value = "Todo added successfully"
        }
    }

    fun update(todo: Todo){
        viewModelScope.launch {
            repository.update(todo)
            message.value = "Todo updated successfully"
        }
    }

    fun delete(todo: Todo){
        viewModelScope.launch {
            repository.delete(todo)
            message.value = "Todo deleted successfully"
        }
    }

    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
            message.value = "Todos cleared successfully"
        }
    }

    fun setupUpdateAndDelete(todo: Todo){
        tvTaskName.value = todo.task
        tvTaskDescription.value = todo.description

        isUpdateOrDelete =true
        todoToUpdateOrDelete = todo

        btnSaveOrUpdate.value = "Update"
        btnDeleteOrClearAll.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}