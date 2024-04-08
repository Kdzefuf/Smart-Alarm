package com.example.smartalarm

import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartalarm.databinding.ActivityMainBinding
import com.example.smartalarm.ui.theme.SmartAlarmTheme

class MainActivity : ComponentActivity(), SleepTrackerView.Listener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sleepTrackerView.listener = this

//        val textView = TextView(this)
//        val timePicker = findViewById<TimePicker>(R.id.time_picker)
//        timePicker.setIs24HourView(true)
//
//        val layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//
//        timePicker.layoutParams = layoutParams
//        timePicker.setOnTimeChangedListener { _, hour, minute->
//            var hour = hour
//
//            if (textView != null) {
//                val hour = if (hour < 10) "0$hour" else hour
//                val min = if (minute < 10) "0$minute" else minute
//
//                val msg = "$hour : $min"
//                textView.text = msg
//                textView.visibility = ViewGroup.VISIBLE
//            }
//        }

//        val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
//        linearLayout?.addView(timePicker)
//        linearLayout?.addView(textView)
    }

    override fun onClick(firstIndex: Int, secondIndex: Int) {
        binding.startTime.text = ((firstIndex + 6) % 24).toString() + ":00"
        binding.endTime.text = ((secondIndex + 6) % 24).toString() + ":00"
    }
}