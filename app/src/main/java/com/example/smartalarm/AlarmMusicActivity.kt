package com.example.smartalarm

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity

class AlarmMusicActivity : ComponentActivity() {
    companion object {
        private var alarmSound = R.raw.one
        private var alarmSoundText = ""

        fun getAlarmSound() : Int {
            if (alarmSound == 0) {
                alarmSound = R.raw.one
                return alarmSound
            }
            else {
                return alarmSound
            }
        }

        fun getAlarmSoundText() : String {
            return alarmSoundText
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_music_activity)

        val firstSound = R.raw.one
        val secondSound = R.raw.two
        val thirdSound = R.raw.three
        val forthSound = R.raw.four
        val fifthSound = R.raw.five
        val sixthSound = R.raw.six
        val seventhSound = R.raw.seven
        val eighthSound = R.raw.eight
        val ninthSound = R.raw.nine
        val tenthSound = R.raw.ten

        var mediaPlayer = MediaPlayer.create(this, firstSound)

        val returnButton = findViewById<Button>(R.id.button)
        returnButton.setOnClickListener{
            mediaPlayer.stop()
            val returnIntent = Intent(this,AlarmActivity::class.java)
            startActivity(returnIntent)
        }

        val firstSoundButton = findViewById<RadioButton>(R.id.alarmSound1)
        val secondSoundButton = findViewById<RadioButton>(R.id.alarmSound2)
        val thirdSoundButton = findViewById<RadioButton>(R.id.alarmSound3)
        val forthSoundButton = findViewById<RadioButton>(R.id.alarmSound4)
        val fifthSoundButton = findViewById<RadioButton>(R.id.alarmSound5)
        val sixthSoundButton = findViewById<RadioButton>(R.id.alarmSound6)
        val seventhSoundButton = findViewById<RadioButton>(R.id.alarmSound7)
        val eighthSoundButton = findViewById<RadioButton>(R.id.alarmSound8)
        val ninthSoundButton = findViewById<RadioButton>(R.id.alarmSound9)
        val tenthSoundButton = findViewById<RadioButton>(R.id.alarmSound10)

        firstSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, firstSound)
            playAlarmSound(mediaPlayer)
            alarmSound = firstSound
            alarmSoundText = findViewById<TextView>(R.id.soundText1).text.toString()
        }
        secondSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, secondSound)
            playAlarmSound(mediaPlayer)
            alarmSound = secondSound
            alarmSoundText = findViewById<TextView>(R.id.soundText2).text.toString()
        }
        thirdSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, thirdSound)
            playAlarmSound(mediaPlayer)
            alarmSound = thirdSound
            alarmSoundText = findViewById<TextView>(R.id.soundText3).text.toString()
        }
        forthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, forthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = forthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText4).text.toString()
        }
        fifthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, fifthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = fifthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText5).text.toString()
        }
        sixthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, sixthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = sixthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText6).text.toString()
        }
        seventhSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, seventhSound)
            playAlarmSound(mediaPlayer)
            alarmSound = seventhSound
            alarmSoundText = findViewById<TextView>(R.id.soundText7).text.toString()
        }
        eighthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, eighthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = eighthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText8).text.toString()
        }
        ninthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, ninthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = ninthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText9).text.toString()
        }
        tenthSoundButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, tenthSound)
            playAlarmSound(mediaPlayer)
            alarmSound = tenthSound
            alarmSoundText = findViewById<TextView>(R.id.soundText10).text.toString()
        }
    }

    private fun playAlarmSound(mediaPlayer: MediaPlayer) {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            mediaPlayer.isLooping = false
        } else {
            mediaPlayer.pause()
        }
    }
}
