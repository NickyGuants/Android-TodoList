package com.example.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val recyclerView =findViewById<RecyclerView>(R.id.recycle_view)
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addBtn = findViewById<Button>(R.id.button)
        val edit = findViewById<EditText>(R.id.edit_text)
        addBtn.setOnClickListener{
            val todoTitle = edit.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                edit.text.clear()
            }
        }

        val deleteButton= findViewById<Button>(R.id.delete_button)
        deleteButton.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }

    }
}