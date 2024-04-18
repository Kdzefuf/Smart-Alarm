package com.example.smartalarm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.example.smartalarm.databinding.ActivityMainBinding

class MainActivity : ComponentActivity(), SleepTrackerView.Listener {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sleepTrackerView.listener = this

        val button = findViewById<Button>(R.id.setAlarm)
        button.setOnClickListener{
            val intent = Intent(this,AlarmActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(firstIndex: Int, secondIndex: Int) {
        binding.startTime.text = ((firstIndex + 6) % 24).toString() + ":00"
        binding.endTime.text = ((secondIndex + 6) % 24).toString() + ":00"
    }

    override fun WheelDatePicker(any: Any) {

    }
}