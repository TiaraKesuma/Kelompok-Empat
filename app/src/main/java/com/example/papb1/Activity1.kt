package com.example.papb1

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.papb1.R.layout.activity_1)

        btnToActivity2.setOnClickListener{
            Intent(this, Activity2::class.java).also {
                startActivity(it)
            }
        }
        println("onCreate")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

}