package com.appvengers.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appvengers.todo.databinding.ActivityTodoBinding
import com.appvengers.todo.db.Todo
import com.appvengers.todo.db.TodoDatabase
import com.appvengers.todo.repository.TodoRepositoryImpl

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var adapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo)

        val dao = TodoDatabase.getInstance(application).todoDao
        val repository = TodoRepositoryImpl(dao)
        val factory = TodoViewModelFactory(repository)


        todoViewModel = ViewModelProvider(this,factory).get(TodoViewModel::class.java)

        binding.todoVM = todoViewModel


        binding.lifecycleOwner = this

        todoViewModel.getNotificationMessage().observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }

       initListView()
    }

    fun initListView(){
        binding.lvTasks.layoutManager = LinearLayoutManager(this)
        displayTodoList()
    }

    fun displayTodoList(){
        adapter = TodoListAdapter() { selectedItem: Todo ->
            onListItemClick(
                selectedItem
            )
        }
        binding.lvTasks.adapter =  adapter

        todoViewModel.todos.observe(this) {
           adapter.updateList(it)
       }

    }

    fun onListItemClick(todo: Todo){
        todoViewModel.setupUpdateAndDelete(todo)
    }
}