package com.example.trollo.ui.infoCenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trollo.R

class InfoActivity: AppCompatActivity() {
    private val tag = "Info Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        Log.d(tag, "Lifecycle: OnCreate")
    }

    override fun onStart() {
        Log.d(tag, "Lifecycle: onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(tag, "Lifecycle: onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(tag, "Lifecycle: onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(tag, "Lifecycle: onDestroy")
        super.onDestroy()
    }
}