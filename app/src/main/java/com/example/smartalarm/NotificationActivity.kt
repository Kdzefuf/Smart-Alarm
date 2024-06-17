package com.example.smartalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class NotificationActivity : ComponentActivity() {
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_activity)

        val stopAlarmButton = findViewById<Button>(R.id.stopAlarmButton)
        stopAlarmButton.setOnClickListener {
            AlarmReceiver.stopMediaPlayer()
            this.finishAffinity()
        }

        val repeatAlarmButton = findViewById<Button>(R.id.repeatAlarmButton)
        repeatAlarmButton.setOnClickListener {
            AlarmReceiver.pauseMediaPlayer()
            setRepeatAlarm(this)
        }
    }

    private fun setRepeatAlarm(context: Context) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 600000,
            pendingIntent
        )

        Toast.makeText(context, "Повтор будильника через 10 минут", Toast.LENGTH_SHORT).show()
    }
}