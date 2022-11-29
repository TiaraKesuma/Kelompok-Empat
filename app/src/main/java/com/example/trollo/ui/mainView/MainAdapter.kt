package com.example.trollo.ui.mainView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.trollo.R
import com.example.trollo.data.db.Task
import kotlinx.android.synthetic.main.task_item.view.*

class MainAdapter(taskEvents: TaskEvents): RecyclerView.Adapter<MainAdapter.ViewHolder>(), Filterable {

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

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, listener: TaskEvents) {
            itemView.idv_title.text = task.title
            itemView.idv_description.text = task.description
            itemView.idv_due_date.text = task.due_date

            itemView.idv_delete.setOnClickListener {
                listener.onDeleteClicked(task)
            }

            itemView.setOnClickListener {
                listener.onUpdateClicked(task)
            }
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

    /**
     * Method used for list updates on Main Activity
     */
    fun setAllTaskItems(tasks: List<Task>) {
        this.taskList = tasks
        this.filteredTaskList = tasks
        notifyDataSetChanged()
    }

    /**
     * Events for user interaction on RecyclerView
     */
    interface TaskEvents {
        fun onDeleteClicked(task: Task)
        fun onUpdateClicked(task: Task)
    }
}