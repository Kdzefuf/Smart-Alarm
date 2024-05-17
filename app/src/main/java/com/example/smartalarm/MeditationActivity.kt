package com.example.smartalarm

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MeditationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meditation_activity)

        val openAlarmsButton = findViewById<Button>(R.id.alarm)
        openNewActivity(AlarmMenu(), openAlarmsButton)

        val openStatsButton = findViewById<Button>(R.id.stats)
        openNewActivity(StatsActivity(), openStatsButton)

        val openMainButton = findViewById<Button>(R.id.tracker)
        openNewActivity(MainActivity(), openMainButton)

        val openSettingsButton = findViewById<Button>(R.id.settings)
        openNewActivity(SettingsActivity(), openSettingsButton)

        val dropsDance = R.raw.drops_dance
        val shower = R.raw.shower
        val bonfire = R.raw.bonfire
        val rainWalk = R.raw.rain_walk
        val seaDepths = R.raw.sea_depths
        val surfSound = R.raw.surf_sound
        val thunder = R.raw.thunder
        val train = R.raw.train
        val universe = R.raw.universe
        val wind = R.raw.wind

        val dropsDanceButton = findViewById<Button>(R.id.soundButton1)
        val showerButton = findViewById<Button>(R.id.soundButton2)
        val rainWalkButton = findViewById<Button>(R.id.soundButton3)
        val thunderButton = findViewById<Button>(R.id.soundButton4)
        val seaDepthsButton = findViewById<Button>(R.id.soundButton5)
        val surfSoundButton = findViewById<Button>(R.id.soundButton6)
        val universeButton = findViewById<Button>(R.id.soundButton7)
        val bonfireButton = findViewById<Button>(R.id.soundButton8)
        val windButton = findViewById<Button>(R.id.soundButton9)
        val trainButton = findViewById<Button>(R.id.soundButton10)
        var mediaPlayer = MediaPlayer.create(this, dropsDance)

        dropsDanceButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, dropsDance)
            playMeditationAudio(mediaPlayer, dropsDanceButton, R.drawable.drops_dance)
        }
        showerButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, shower)
            playMeditationAudio(mediaPlayer, showerButton, R.drawable.shower)
        }
        rainWalkButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, rainWalk)
            playMeditationAudio(mediaPlayer, rainWalkButton, R.drawable.rain_walk)
        }
        thunderButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, thunder)
            playMeditationAudio(mediaPlayer, thunderButton, R.drawable.thunder)
        }
        seaDepthsButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, seaDepths)
            playMeditationAudio(mediaPlayer, seaDepthsButton, R.drawable.sea_deeps)
        }
        surfSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, surfSound)
            playMeditationAudio(mediaPlayer, surfSoundButton, R.drawable.surf_sound)
        }
        universeButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, universe)
            playMeditationAudio(mediaPlayer, universeButton, R.drawable.universe)
        }
        bonfireButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, bonfire)
            playMeditationAudio(mediaPlayer, bonfireButton, R.drawable.bonfire)
        }
        windButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, wind)
            playMeditationAudio(mediaPlayer, windButton, R.drawable.wind)
        }
        trainButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, train)
            playMeditationAudio(mediaPlayer, trainButton, R.drawable.train_drive)
        }
    }

    private fun playMeditationAudio(mediaPlayer: MediaPlayer, soundButton: Button, image: Int) {
        val playButton = findViewById<ImageView>(R.id.playPauseAudioButton)
        val audioLayout = findViewById<LinearLayout>(R.id.audioLayout)
        val cancelAudioButton = findViewById<ImageView>(R.id.cancelAudioButton)
        val audioImage = findViewById<ImageView>(R.id.audioImage)
        val audioText = findViewById<TextView>(R.id.audioText)

        audioLayout.visibility = View.VISIBLE
        audioImage.setImageResource(image)
        audioText.text = soundButton.text
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            mediaPlayer.isLooping = true
            playButton.setImageResource(R.drawable.pause)
            if (mediaPlayer.duration > 1800000) {
                mediaPlayer.stop()
            }
        } else {
            mediaPlayer.pause()
            playButton.setImageResource(R.drawable.play)
        }

        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                playButton.setImageResource(R.drawable.pause)
                if (mediaPlayer.duration > 1800000) {
                    mediaPlayer.stop()
                }
            } else {
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.play)
            }
        }

        cancelAudioButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            audioLayout.visibility = View.INVISIBLE
        }
    }

    private fun openNewActivity(activity: ComponentActivity, button: Button) {
        button.setOnClickListener {
            val intent = Intent(this, activity::class.java)
            startActivity(intent)
        }
    }
}
