package com.example.trollo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trollo.model.Task
import com.example.trollo.viewModel.TaskListViewModel
import java.nio.file.Files.delete

class TasksAdapter(
    val viewModel: TaskListViewModel,
    val arrayList: ArrayList<Task>,
    val context: Context

    ) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return TasksViewHolder(root)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if(arrayList.size == 0){
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayList.size
    }

    inner class TasksViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(task: Task) {
            binding.findViewById<TextView>(R.id.title).text = task.title
            binding.findViewById<ImageButton>(R.id.delete_button).setOnClickListener {
                viewModel.deleteTask(task)
                notifyItemRemoved(arrayList.indexOf(task))
            }

        }
    }
}