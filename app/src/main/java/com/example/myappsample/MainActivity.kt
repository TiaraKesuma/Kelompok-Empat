package com.example.myappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * An activity that inflates a layout that has a NavHostFragment.
 */
class MainActivity : AppCompatActivity() {
    companion object GameState{
        /* Hands value assignment
            Rock: 1
            Paper: 2
            Scissor: 3
        */
        var myHand = 0
        var enemyHand = 0
        var difficulty = 0

        fun computeHand() {
            if(difficulty == 1) { // normal difficulty
                enemyHand = (1..3).random() // generate random hand
            }

            if(difficulty == 2) { // impossible difficulty
                if(myHand == 1) enemyHand = 2
                if(myHand == 2) enemyHand = 3
                if(myHand == 3) enemyHand = 1
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
            return flag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}