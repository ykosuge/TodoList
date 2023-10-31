package com.sonix.todoapp.presentaiton.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sonix.todoapp.databinding.ItemListBinding
import com.sonix.todoapp.model.Todo

class ListAdapter(
    private val onClickView: (Todo) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    /** Todoの一覧 */
    var todoList = emptyList<Todo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        when (val binding = holder.binding) {
            is ItemListBinding -> {
                binding.also {
                    it.textView.text = todo.title
                    it.root.setOnClickListener { onClickView(todo) }
                }
            }
            else -> throw IllegalArgumentException("$binding is not supported.")
        }
    }

    override fun getItemCount(): Int = todoList.size

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}
