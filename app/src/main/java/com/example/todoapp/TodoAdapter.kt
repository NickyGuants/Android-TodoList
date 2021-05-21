package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R.layout.item_todo

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val todoItem: TextView = itemView.findViewById(R.id.todo_item)
        val checkComplete: CheckBox = itemView.findViewById(R.id.complete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                item_todo,
                parent,
                false
            )
        )
    }
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }
    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(todoItem: TextView, isChecked: Boolean){
        if(isChecked){
            todoItem.paintFlags = todoItem.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            todoItem.paintFlags = todoItem.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.todoItem.text =curTodo.title
        holder.checkComplete.isChecked = curTodo.isChecked
        toggleStrikeThrough(todoItem = holder.todoItem, isChecked = curTodo.isChecked)
        holder.checkComplete.setOnCheckedChangeListener{_, isChecked ->
            toggleStrikeThrough(holder.todoItem, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
        /**holder.itemView.apply {

            todo_item.text = curTodo.title
            checkComplete.isChecked = curTodo.isChecked
        }**/
    }


    override fun getItemCount(): Int {
        return todos.size
    }
}