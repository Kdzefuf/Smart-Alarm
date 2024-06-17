package com.example.smartalarm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class StatsDayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stats_day_activity)

        val openStatsWeekButton = findViewById<Button>(R.id.statsWeekButton)
        openStatsWeekButton.setOnClickListener {
            val openStatsWeekIntent = Intent(this, StatsActivity::class.java)
            startActivity(openStatsWeekIntent)
        }

        val openAlarmsButton = findViewById<Button>(R.id.alarm)
        openAlarmsButton.setOnClickListener {
            val openAlarmsIntent = Intent(this, AlarmMenu::class.java)
            startActivity(openAlarmsIntent)
        }

        val openMainButton = findViewById<Button>(R.id.tracker)
        openMainButton.setOnClickListener {
            val openMainIntent = Intent(this, MainActivity::class.java)
            startActivity(openMainIntent)
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
}
