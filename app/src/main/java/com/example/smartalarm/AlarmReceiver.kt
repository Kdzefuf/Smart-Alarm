package com.example.smartalarm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private var mediaPlayer: MediaPlayer? = null

        fun stopMediaPlayer() {
            mediaPlayer!!.stop()
        }

        fun pauseMediaPlayer() {
            mediaPlayer!!.pause()
        }
    }


    @SuppressLint("MissingPermission", "UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE)
        Log.d("hdshjhjkds", mediaPlayer.toString())

        try {
            showNotification(context, "Будильник", "Время просыпаться", pendingIntent)
        } catch (ex: Exception) {
            Log.d("Receive Ex", "onReceive: ${ex.printStackTrace()}")
        }
    }

    private fun showNotification(context: Context?, title: String, description: String, pendingIntent: PendingIntent) {
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
            .setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, NotificationActivity::class.java), PendingIntent.FLAG_IMMUTABLE))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        manager.notify(1, builder)
        playNotificationSound(context)
    }

    private fun playNotificationSound(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, AlarmMusicActivity.getAlarmSound())
            Log.d("dsjdsjkdss", MediaPlayer.create(context, AlarmMusicActivity.getAlarmSound()).toString())
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else {
            Log.d("jkdshdkjs", mediaPlayer.toString())
            mediaPlayer!!.start()
        }
    }
}