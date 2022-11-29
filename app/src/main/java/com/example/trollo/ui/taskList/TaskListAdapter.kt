package com.example.trollo.ui.taskList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trollo.R
import com.example.trollo.model.db.Task
import kotlinx.android.synthetic.main.task_item.view.*

class TaskListAdapter(taskEvents: TaskEvents) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>(), Filterable {

    private var taskList: List<Task> = arrayListOf()
    private var filteredTaskList: List<Task> = arrayListOf()
    private var listener: TaskEvents = taskEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filteredTaskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTaskList[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, listener: TaskEvents) {
            itemView.task_title.text = task.title
            itemView.task_desc.text = task.description
            itemView.task_due_date.text = task.due_date

            itemView.item_delete.setOnClickListener {
                listener.onDeleteClicked(task)
            }

            itemView.setOnClickListener {
                listener.onEditClicked(task)
            }
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    fun setAllTaskItems(taskItems: List<Task>) {
        this.taskList = taskItems
        this.filteredTaskList = taskItems
        notifyDataSetChanged()
    }

    /**
     * RecyclerView user interaction events
     */
    interface TaskEvents {
        fun onDeleteClicked(task: Task)
        fun onEditClicked(task: Task)
    }
}