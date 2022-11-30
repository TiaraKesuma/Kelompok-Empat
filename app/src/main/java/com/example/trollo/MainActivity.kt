package com.example.trollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trollo.model.Task
import com.example.trollo.viewModel.TaskListViewModel
import com.example.trollo.viewModel.TaskListViewModelFactory

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel : TaskListViewModel
    private lateinit var mainrecycler : RecyclerView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainrecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = TaskListViewModelFactory()
        val recyc = findViewById<LinearLayout>(R.layout.task_list)
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
        button = findViewById(R.id.tambahlist)
        button.setOnClickListener {

            addData()
        }

        initialiseAdapter()

    }
    private fun initialiseAdapter(){
        mainrecycler.layoutManager= viewManager
        observeData()
    }

    fun observeData(){
        viewModel.list.observe(this, Observer{
            Log.i("data",it.toString())
            mainrecycler.adapter= TasksAdapter(viewModel, it, this)
        })
    }


    fun addData(){

        var kotakjudul = findViewById<EditText>(R.id.judul)
        var kotakdeskripsi = findViewById<EditText>(R.id.deskripsi)
        var kotakdeadline = findViewById<EditText>(R.id.deadline)
        var judul=kotakjudul.text.toString()
        var deskripsi=kotakdeskripsi.text.toString()
        var deadline=kotakdeadline.text.toString()
        if(judul.isNullOrBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            var blog= Task(judul,deskripsi,deadline)
            viewModel.addTask(blog)
            kotakjudul.text.clear()
            kotakdeskripsi.text.clear()
            kotakdeadline.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }

    }
}