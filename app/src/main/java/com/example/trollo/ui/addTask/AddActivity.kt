package com.example.trollo.ui.addTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.trollo.R
import com.example.trollo.data.db.Task
import com.example.trollo.utils.Const
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private val tag = "AddActivity"
    var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // if intent extra exist, prefill form with received data
        val intent = intent
        if (intent != null && intent.hasExtra(Const.INTENT_OBJECT)) {
            val task: Task? = intent.getParcelableExtra(Const.INTENT_OBJECT)
            this.task = task
            if (task != null) {
                preFillForm(task)
            }
        }

        dialog_title.text = if (task != null) getString(R.string.update_task)
            else getString(R.string.add_task)

        // set click listener on the buttons
        item_add_button.setOnClickListener {
            saveTask()
        }

        item_cancel_button.setOnClickListener {
            goBack()
        }
    }

    private fun preFillForm(task: Task) {
        Log.d(tag, "Task Title: ${item_title.text}")
        item_title.setText(task.title)
        item_description.setText(task.description)
        item_due_date.setText(task.due_date)
    }

    private fun saveTask() {
        if(validateForm()) {
            Log.i(tag, "Saving Task...")
            Log.d(tag, "Task Title: ${item_title.text}")
            val id = if (task != null) task?.id else null
            val task = Task(id = id, title = item_title.text.toString(),
                description = item_description.text.toString(),
                due_date = item_due_date.text.toString())
            val intent = Intent()
            intent.putExtra(Const.INTENT_OBJECT, task)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validateForm(): Boolean {
        val errorMessages: MutableList<String> = ArrayList()
        Log.d(tag, "initiliazing errorMessages. Size: ${errorMessages.size}")
        if (item_title.text.isEmpty()) {
            errorMessages.add(getString(R.string.title_error))
        }
        if (item_description.text.isEmpty()) {
            errorMessages.add(getString(R.string.description_error))
        }
        if (item_due_date.text.isEmpty()) {
            errorMessages.add(getString(R.string.due_date_error_1))
        }
        else if (item_due_date.text.toString().matches(Regex("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d\$"))) {
            errorMessages.add(getString(R.string.due_date_error_2))
        }
        Log.d(tag, "Finish validating. ErrorMessages size: ${errorMessages.size}")
        if (errorMessages.size > 0) {
            for(error in errorMessages) {
                val toast = Toast.makeText(applicationContext, error, Toast.LENGTH_LONG)
                toast.show()
            }
        }
        return errorMessages.size <= 0
    }

    private fun goBack() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }
}