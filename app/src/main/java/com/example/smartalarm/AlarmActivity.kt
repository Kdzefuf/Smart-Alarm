package com.example.smartalarm

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Switch
import androidx.activity.ComponentActivity

class AlarmActivity : ComponentActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var switch = findViewById<Switch>(R.id.repeatSwitch)
        switch.thumbTintMode = PorterDuff.Mode.SRC_IN

        switch.trackTintMode = PorterDuff.Mode.SCREEN

        var layout = findViewById<RelativeLayout>(R.id.mainLayout)

//        setContent {
//
//            val checkedState =  remember { mutableStateOf(false) }
//            Column {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Switch(
//                        checked = checkedState.value,
//                        onCheckedChange = {
//                            checkedState.value = it
//                        },
//                        colors = SwitchDefaults.colors(
//                            checkedThumbColor = Color(0xFFFFFFFF),
//                            checkedTrackColor = Color(0xFF776AA6),
//                            uncheckedThumbColor = Color(0xFFFFFFFF),
//                            uncheckedTrackColor = Color(0xFF000000)
//                        )
//                    )
//                }
//            }
//        }
    }
}