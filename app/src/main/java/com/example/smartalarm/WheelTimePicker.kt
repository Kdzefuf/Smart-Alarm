package com.example.smartalarm

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.smartalarm.wheelTimePicker.DefaultWheelTimePicker
import com.example.smartalarm.wheelTimePicker.SelectorProperties
import com.example.smartalarm.wheelTimePicker.TimeFormat
import com.example.smartalarm.wheelTimePicker.WheelPickerDefaults
import java.time.LocalTime

class WheelTimePicker(
    context: Context?,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun SetWheelTimePicker(
        modifier: Modifier = Modifier,
        startTime: LocalTime = LocalTime.now(),
        minTime: LocalTime = LocalTime.MIN,
        maxTime: LocalTime = LocalTime.MAX,
        timeFormat: TimeFormat = TimeFormat.HOUR_24,
        size: DpSize = DpSize(128.dp, 128.dp),
        rowCount: Int = 3,
        textStyle: TextStyle = MaterialTheme.typography.titleMedium,
        textColor: Color = LocalContentColor.current,
        selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
        onSnappedTime : (snappedTime: LocalTime) -> Unit = {},
    ) {
        DefaultWheelTimePicker(
            modifier,
            startTime,
            minTime,
            maxTime,
            timeFormat,
            size,
            rowCount,
            textStyle,
            textColor,
            selectorProperties,
            onSnappedTime = { snappedTime, _ ->
                onSnappedTime(snappedTime.snappedLocalTime)
                snappedTime.snappedIndex
            }
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}

