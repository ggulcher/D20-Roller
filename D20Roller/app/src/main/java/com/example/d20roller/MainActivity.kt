package com.example.d20roller

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var numDice = 1
    private var amountBonus = 0
    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDice()
        selectBonus()
    }

    private fun selectDice() {
        numberDice.text = "${numDice}d"
        diceAdd.setOnClickListener{
            numDice++
            numberDice.text = "${numDice}d"
        }
        diceSubtract.setOnClickListener{
            numDice--
            numberDice.text = "${numDice--}d"
        }
    }

    private fun selectBonus() {
        rollBonus.text = "${amountBonus}"
        bonusAdd.setOnClickListener{
            amountBonus++
            rollBonus.text = "${amountBonus}"
        }
        bonusSubtract.setOnClickListener{
            amountBonus--
            rollBonus.text = "${amountBonus}"
        }
    }

    private fun playSoundFile(fileName: Int) {
        mediaPlayer = MediaPlayer.create(this, fileName)
        mediaPlayer?.start()
    }

    private fun stopSoundFile() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onClick(v: View?) {
        val buttonValue = when (v?.id) {
            R.id.d4 -> 4
            R.id.d6 -> 6
            R.id.d8 -> 8
            R.id.d10 -> 10
            R.id.d12 -> 12
            R.id.d20 -> 20
            else -> 100
        }
        val dice = Dice(buttonValue)
        val diceRoll = ((dice.roll() + amountBonus)*numDice)

        rollResult.text = diceRoll.toString()
        if (buttonValue == 20 && diceRoll == 20) {
            playSoundFile(R.raw.fanfare)
        } else {
            playSoundFile(R.raw.diceroll)
        }
        mediaPlayer?.setOnCompletionListener { stopSoundFile() }
    }
}

class Dice(private var numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

