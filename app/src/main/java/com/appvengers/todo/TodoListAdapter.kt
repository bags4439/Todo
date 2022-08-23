package com.appvengers.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appvengers.todo.databinding.TodoListItemBinding
import com.appvengers.todo.db.Todo

class TodoListAdapter(
    private val onClickCallback: (Todo)->Unit
) :RecyclerView.Adapter<TodoViewHolder>(){
    private var todos = ArrayList<Todo>();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: TodoListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.todo_list_item,parent, false)

        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position], onClickCallback)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    public fun updateList(newTodos: List<Todo>){
        todos.clear()
        todos.addAll(newTodos)
    }
}

class TodoViewHolder(
    val binding: TodoListItemBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(todo: Todo, onClickCallback: (Todo) -> Unit){
            binding.tvHeader.text = todo.task
            binding.tvBody.text = todo.description
            binding.root.setOnClickListener {
                onClickCallback(todo)
            }
        }
}