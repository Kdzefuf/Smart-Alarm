package com.example.smartalarm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import com.wangjie.wheelview.WheelView

class RepeatActivity : ComponentActivity() {
    companion object {
        private var repeatCount = 0
        private var repeatMinutes = 0

        fun getRepeatCount() : Int {
            return repeatCount
        }

        fun getRepeatMinutes() : Int {
            return repeatMinutes
        }

        private const val TAG = "repeat_minutes"
        private val MINUTES = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repeat_activity)
        val wva = findViewById<WheelView>(R.id.wheel_view)
        wva.offset = 1
        wva.setItems(MINUTES)
        wva.onWheelViewListener = object  : WheelView.OnWheelViewListener(){
            override fun onSelected(index:Int, item:String?) {
                Log.d(TAG, "selectedIndex: $index, item: $item")
            }
        }
        val returnButton = findViewById<Button>(R.id.button)
        returnButton.setOnClickListener{
            val returnIntent = Intent(this,AlarmActivity::class.java)
            startActivity(returnIntent)
        }

        val repeatCount3Button = findViewById<RadioButton>(R.id.repeatCount3)
        repeatCount3Button.setOnClickListener {
            repeatCount = 3
        }

        val repeatCount5Button = findViewById<RadioButton>(R.id.repeatCount5)
        repeatCount5Button.setOnClickListener {
            repeatCount = 5
        }

        val repeatCountInfButton = findViewById<RadioButton>(R.id.repeatCountInf)
        repeatCountInfButton.setOnClickListener {
            repeatCount = 100
        }

        val repeat5MinuteButton = findViewById<RadioButton>(R.id.repeat5Minutes)
        repeat5MinuteButton.setOnClickListener {
            repeatMinutes = 5
        }

        val repeat10MinuteButton = findViewById<RadioButton>(R.id.repeat10Minutes)
        repeat10MinuteButton.setOnClickListener {
            repeatMinutes = 10
        }

        val repeat15MinuteButton = findViewById<RadioButton>(R.id.repeat15Minutes)
        repeat15MinuteButton.setOnClickListener {
            repeatMinutes = 15
        }

        val repeat30MinuteButton = findViewById<RadioButton>(R.id.repeat30Minutes)
        repeat30MinuteButton.setOnClickListener {
            repeatMinutes = 30
        }

        val repeatOtherMinuteButton = findViewById<RadioButton>(R.id.repeatElse)
        repeatOtherMinuteButton.setOnClickListener {
            repeatMinutes = wva.selectedIndex
        }
    }
}