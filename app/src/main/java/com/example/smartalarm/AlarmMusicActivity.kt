package com.example.smartalarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class AlarmMusicActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_music_activity)

        val returnButton = findViewById<Button>(R.id.button)
        returnButton.setOnClickListener{
            val returnIntent = Intent(this,AlarmActivity::class.java)
            startActivity(returnIntent)
        }
    }
}
