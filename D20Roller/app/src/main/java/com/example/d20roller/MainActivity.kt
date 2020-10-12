package com.example.d20roller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        var buttonValue = when (v?.id) {
            R.id.d4 -> 4
            R.id.d6 -> 6
            R.id.d8 -> 8
            R.id.d10 -> 10
            R.id.d12 -> 12
            else -> 20
        }

        var dice = Dice(buttonValue)
        var diceRoll = dice.roll()

        rollResult.setText(diceRoll.toString())

    }
}

class Dice(private var numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}