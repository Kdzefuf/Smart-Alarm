package com.example.smartalarm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

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

        val openMainButton = findViewById<Button>(R.id.tracker)
        openMainButton.setOnClickListener {
            val openMainIntent = Intent(this, MainActivity::class.java)
            startActivity(openMainIntent)
        }
    }
}
