package com.example.myappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

/**
 * An activity that inflates a layout that has a NavHostFragment.
 */

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    companion object GameState{
        /* Hands value assignment
            Rock: 1
            Paper: 2
            Scissor: 3
        */
        private const val TAGGS = "GameState"
        var myHand = 0
        var enemyHand = 0
        var difficulty = 0

        fun chooseHand(player : Boolean = true, hand : Int) {
            var unit = "none"
            var handName = "none"

            if (player) {
                myHand = hand
                unit = "Player"
            }
            else {
                enemyHand = hand
                unit = "AI"
            }

            when (hand) {
                1 -> handName = "Rock"
                2 -> handName = "Paper"
                3 -> handName = "Scissor"
            }

            Log.i(TAGGS,"$unit chooses $handName")
        }

        fun chooseDifficulty(diff : Int) {
            var difficulty = "none"
            this.difficulty = diff
            when (diff) {
                1 -> difficulty = "Normal"
                2 -> difficulty = "Impossible"
            }
            Log.i(TAGGS,"Difficulty: $difficulty")
        }

        fun computeHand() {
            Log.d(TAGGS, "Computing hands")
            if(difficulty == 1) { // normal difficulty
                chooseHand(false, (1..3).random()) // generate random hand
            }

            if(difficulty == 2) { // impossible difficulty
                if(myHand == 1) chooseHand(false,2)
                if(myHand == 2) chooseHand(false,3)
                if(myHand == 3) chooseHand(false,1)
            }
        }

        fun getHandDrawables(hand : Int) : Int {
            var drawable = 0 // debug value
            when (hand) {
                1 -> drawable = R.drawable.icon_hand_rock
                2 -> drawable = R.drawable.icon_hand_paper
                3 -> drawable = R.drawable.icon_hand_scissor
            }
            return drawable
        }

        fun getResult() : Int {
            var flag = 0 // debug value
            if(myHand == enemyHand + 1 || (myHand == 1 && enemyHand == 3)) flag = R.string.win // win
            else if(myHand == enemyHand - 1 || (myHand == 3 && enemyHand == 1)) flag = R.string.lose // lose
            else if(myHand == enemyHand) flag = R.string.draw // draw
            Log.d(TAGGS,"Result: $flag")
            return flag
        }
    }
    private var oldTime : Long = 0L
    private var newTime : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Lifecycle:  OnCreate")
        super.onCreate(savedInstanceState)
        oldTime = 0L
        newTime = 0L
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.d(TAG, "Lifecycle: onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "Lifecycle: onResume")
        Log.d(TAG, "OldTime value: $oldTime")
        if (oldTime != 0L) {
            newTime = System.nanoTime()
            Log.d(TAG, "NewTime value: $newTime")
            val elapsedSeconds = (newTime - oldTime) / 1000000000
            Log.d(TAG, "Elapsed time onStop: $elapsedSeconds")
            Toast.makeText(applicationContext, "You have been gone for $elapsedSeconds seconds", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "TimeToast fired")
        }
        else {
            Toast.makeText(applicationContext, getText(R.string.welcome_toast), Toast.LENGTH_SHORT).show()
            Log.d(TAG, "WelcomeToast fired")
        }
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Lifecycle: onPause")
        super.onPause()
        oldTime = System.nanoTime()
        Log.d(TAG, "OldTime value: $oldTime")
    }

    override fun onStop() {
        Log.d(TAG, "Lifecycle: onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "Lifecycle: onDestroy")
        super.onDestroy()
    }
}