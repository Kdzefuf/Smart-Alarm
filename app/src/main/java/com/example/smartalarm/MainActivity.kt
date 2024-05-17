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

        val setAlarmButton = findViewById<Button>(R.id.setAlarm)
        setAlarmButton.setOnClickListener{
            val setAlarmIntent = Intent(this,AlarmActivity::class.java)
            startActivity(setAlarmIntent)
        }

        val openAlarmsButton = findViewById<Button>(R.id.alarm)
        openAlarmsButton.setOnClickListener {
            val openAlarmsIntent = Intent(this, AlarmMenu::class.java)
            startActivity(openAlarmsIntent)
        }

        val openStatsButton = findViewById<Button>(R.id.stats)
        openStatsButton.setOnClickListener {
            val openStatsIntent = Intent(this, StatsActivity::class.java)
            startActivity(openStatsIntent)
        }

        val openMeditationButton = findViewById<Button>(R.id.music)
        openMeditationButton.setOnClickListener {
            val openMeditationIntent = Intent(this, MeditationActivity::class.java)
            startActivity(openMeditationIntent)
        }

        val openSettingsButton = findViewById<Button>(R.id.settings)
        openSettingsButton.setOnClickListener {
            val openSettingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(openSettingsIntent)
        }
    }

    override fun onClick(firstIndex: Int, secondIndex: Int) {
        binding.startTime.text = ((firstIndex + 6) % 24).toString() + ":00"
        binding.endTime.text = ((secondIndex + 6) % 24).toString() + ":00"
    }

    override fun WheelDatePicker(any: Any) {
        TODO("Not yet implemented")
    }
}