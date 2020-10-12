package com.example.d20roller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var numDice = 1
    private var amountBonus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDice()
        selectBonus()
    }

    private fun selectDice() {
        numberDice.setText("${numDice}d")
        diceAdd.setOnClickListener{ numberDice.setText("${numDice++}d") }
        diceSubtract.setOnClickListener{ numberDice.setText("${numDice--}d") }
    }

    private fun selectBonus() {
        rollBonus.setText("${amountBonus}")
        bonusAdd.setOnClickListener{ rollBonus.setText("${amountBonus++}") }
        bonusSubtract.setOnClickListener{ rollBonus.setText("${amountBonus--}") }
    }

    override fun onClick(v: View?) {
        var buttonValue = when (v?.id) {
            R.id.d4 -> 4
            R.id.d6 -> 6
            R.id.d8 -> 8
            R.id.d10 -> 10
            R.id.d12 -> 12
            R.id.d20 -> 20
            else -> 100
        }

        var dice = Dice(buttonValue)
        var diceRoll = numDice * (dice.roll() + amountBonus)

        rollResult.setText(diceRoll.toString())
    }
}

class Dice(private var numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
