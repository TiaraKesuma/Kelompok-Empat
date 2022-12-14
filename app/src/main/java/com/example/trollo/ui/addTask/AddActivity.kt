package com.example.trollo.ui.addTask

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.trollo.R
import com.example.trollo.data.db.Task
import com.example.trollo.ui.mainView.MainActivity
import com.example.trollo.utils.Const
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*
import kotlin.collections.ArrayList

class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private val tag = "AddActivity"
    var task: Task? = null
    private lateinit var notificationManager: NotificationManager

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var storedDay = 0
    var storedMonth = 0
    var storedYear = 0
    var storedHour = 0
    var storedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                Const.NOTIFICATION_CHANNEL_ID,
                Const.NOTIFICATION_CHANNEL_NAME,
                importance).apply {
                description = Const.NOTIFICATION_CHANNEL_DESC
            }
            // Register the channel with the system
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

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

        // set click listener for Due Date Picker
        item_due_date.setOnClickListener {
            pickDate()
        }

        // set click listener on the buttons
        item_add_button.setOnClickListener {
            val notificationIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(this, Const.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.trollo_ic_notifs)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(1, builder.build())
            }

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
        item_due_date.text = task.due_date
    }

    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate() {
        getDateTimeCalendar()
        DatePickerDialog(this, this, year, month, day).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        storedDay = dayOfMonth
        storedMonth = month
        storedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        storedHour = hour
        storedMinute = minute

        // set the text
        item_due_date.text = "$storedDay-$storedMonth-$storedYear  $storedHour:$storedMinute"
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
        Log.d(tag, "initializing errorMessages. Size: ${errorMessages.size}")
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