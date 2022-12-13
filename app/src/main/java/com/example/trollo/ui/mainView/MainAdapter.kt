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

    /**
     * Search Filter implementation
     */
    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
//    override fun getFilter(): Filter {
//        return object: Filter() {
//            override fun performFiltering(p0: CharSequence?): FilterResults {
//                val charString = p0.toString()
//                filteredTaskList = if (charString.isEmpty()) {
//                    taskList
//                } else {
//                    val filteredList = arrayListOf<Task>()
//                    for (row in taskList) {
//                        if (row.title.lowercase().contains(charString.lowercase())
//                            || row.description.contains(charString.lowercase())) {
//                            filteredList.add(row)
//                        }
//                    }
//                    filteredList
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = filteredTaskList
//                return filterResults
//            }
//
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                filteredTaskList = p1?.values as List<Task>
//                notifyDataSetChanged()
//            }
//        }
//    }

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