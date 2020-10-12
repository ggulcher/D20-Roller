package com.example.d20roller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Default values saved globally
    private var numDice = 1
    private var amountBonus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Run on startup
        selectDice()
        selectBonus()
    }

    private fun selectDice() {
        // Default amount of dice is 1
        numberDice.setText("${numDice}d")
        // Govern increasing or decreasing the number of dice rolled
        diceAdd.setOnClickListener{
            numDice++
            numberDice.setText("${numDice}d") }
        diceSubtract.setOnClickListener{
            numDice--
            numberDice.setText("${numDice--}d") }
    }

    private fun selectBonus() {
        // Default roll bonus is 0
        rollBonus.setText("${amountBonus}")
        // Govern the amount of bonus added to dice roll
        bonusAdd.setOnClickListener{
            amountBonus++
            rollBonus.setText("${amountBonus}") }
        bonusSubtract.setOnClickListener{
            amountBonus--
            rollBonus.setText("${amountBonus}") }
    }

    override fun onClick(v: View?) {
        // Used to determine size of num range for selecting a number
        var buttonValue = when (v?.id) {
            R.id.d4 -> 4
            R.id.d6 -> 6
            R.id.d8 -> 8
            R.id.d10 -> 10
            R.id.d12 -> 12
            R.id.d20 -> 20
            else -> 100
        }
        // Find final result, considering number of dice and amount of bonus
        var dice = Dice(buttonValue)
        var diceRoll = ((dice.roll() + amountBonus)*numDice)
        // Display final result to TextView
        rollResult.setText(diceRoll.toString())
        // Pseudo code for sound file
        // if (dice.roll() == 20) { play "triumph" }
    }
}

// Separate class for generation of random number used by all dice buttons
class Dice(private var numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
