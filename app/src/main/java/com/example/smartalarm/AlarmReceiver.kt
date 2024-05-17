package com.example.smartalarm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("MissingPermission", "UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            showNotification(context, "Будильник", "Время просыпаться")
        } catch (ex: Exception) {
            Log.d("Receive Ex", "onReceive: ${ex.printStackTrace()}")
        }
    }

    private fun showNotification(context: Context?, title: String, description: String) {
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "alarm_channel"
        val channelName = "alarm"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.wake_up)
            .setAutoCancel(true)
            .setSound(null)

        manager.notify(1, builder.build())
        playNotificationSound(context)
    }

    private fun playNotificationSound(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.alarm)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else {
            mediaPlayer!!.start()
        }
    }
}