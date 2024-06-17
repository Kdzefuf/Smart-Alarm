package com.example.smartalarm

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartalarm.alarm.Alarm
import com.example.smartalarm.alarm.AlarmRoomDatabase
import java.util.Calendar

class AlarmMenu : ComponentActivity() {
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AlarmRoomDatabase.getInstance(this)

        calendar = Calendar.getInstance()

        setContent {
            Column(modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
                ) {
                val context = LocalContext.current
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Мои будильники",
                            color = Color(193, 193, 193),
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
//                        fontFamily = FontFamily(Font(R.font.rubik_medium)),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(vertical = 46.dp)
                                .fillMaxWidth()
                        )
                    }
                    AlarmsColumn(db)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(42, 44, 62))
                    .padding(vertical = 25.dp, horizontal = 20.dp)
                ) {
                    Button(
                        onClick = {
                            val mainIntent = Intent(context, MainActivity::class.java)
                            startActivity(mainIntent) },
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .heightIn(max = 22.dp)
                            .widthIn(22.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = ButtonDefaults.shape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painterResource(id = R.drawable.moon),
                            contentDescription = "Луна",
                            alignment = Alignment.BottomStart
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .heightIn(max = 22.dp)
                            .widthIn(22.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = ButtonDefaults.shape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painterResource(id = R.drawable.timer_select),
                            contentDescription = "Будильник",
                            alignment = Alignment.BottomStart
                        )
                    }
                    Button(
                        onClick = {
                            val openStatsIntent = Intent(context, StatsActivity::class.java)
                            startActivity(openStatsIntent)
                            },
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .heightIn(max = 22.dp)
                            .widthIn(22.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = ButtonDefaults.shape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painterResource(id = R.drawable.tab_menu),
                            contentDescription = "Статистика",
                            alignment = Alignment.BottomStart
                        )
                    }
                    Button(
                        onClick = {
                            val musicIntent = Intent(context, MeditationActivity::class.java)
                            startActivity(musicIntent) },
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .heightIn(max = 22.dp)
                            .widthIn(22.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = ButtonDefaults.shape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painterResource(id = R.drawable.music),
                            contentDescription = "Музыка",
                            alignment = Alignment.BottomStart
                        )
                    }
                    Button(
                        onClick = {
                            val settingsIntent = Intent(context, SettingsActivity::class.java)
                            startActivity(settingsIntent) },
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .heightIn(max = 22.dp)
                            .widthIn(22.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(0.dp),
                        shape = ButtonDefaults.shape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            painterResource(id = R.drawable.setting),
                            contentDescription = "Настройки",
                            alignment = Alignment.BottomStart
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun CreateSwitch(checkedState: MutableState<Boolean>, alarm: Alarm, alarmDb: AlarmRoomDatabase) {
        Switch (
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                setAlarmEnabled(checkedState, alarm, alarmDb)
            },
            modifier = Modifier.padding(top = 8.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFFFFFF),
                checkedTrackColor = Color(0xFF776AA6),
                uncheckedThumbColor = Color(0xFFFFFFFF),
                uncheckedTrackColor = Color(0xFF000000)
            )
        )
    }

    private fun showDeleteAlarmDialog(alarm: Alarm, alarmDb: AlarmRoomDatabase) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.delete_alarm_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.Transparent.toArgb()))

        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val deleteButton = dialog.findViewById<Button>(R.id.deleteButton)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        deleteButton.setOnClickListener {
            Thread {
                alarmDb.getDao().deleteAlarm(alarm)
            }.start()
            Toast.makeText(this, "Будильник удален", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

    @Composable
    private fun AlarmsColumn(alarmDb: AlarmRoomDatabase) {
        val alarmsList = alarmDb.getDao().getAlarms().collectAsState(initial = emptyList())
        LazyColumn {
            items(alarmsList.value) {alarm ->
                AlarmItem(alarm = alarm, alarmDb = alarmDb)
            }
        }
    }

    @Composable
    private fun AlarmItem(alarm: Alarm, alarmDb: AlarmRoomDatabase) {
        val onAlarmState =  remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 30.dp, top = 22.dp, end = 30.dp, bottom = 16.dp)
                .background(Color(42, 44, 62), shape = RoundedCornerShape(20.dp))
                .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 18.dp)
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = alarm.label,
                    modifier = Modifier.fillMaxWidth(0.94f),
                    color = Color(193, 193, 193),
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left
                )
                Button(onClick = { showDeleteAlarmDialog(alarm, alarmDb) },
                    shape = RoundedCornerShape(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1f)
                        .width(27.dp)
                        .height(20.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel_alarm),
                        contentDescription = "cancel",
                        modifier = Modifier
                            .height(18.dp)
                            .width(18.dp),
                        alignment = Alignment.CenterEnd
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = alarm.wakeTime,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 6.dp),
                    color = Color(193, 193, 193),
                    fontWeight = FontWeight(500),
                    fontSize = 36.sp
                )
                onAlarmState.value = isAlarmEnabled(alarm.enabled)
                CreateSwitch(checkedState = onAlarmState, alarm = alarm, alarmDb = alarmDb)
            }
            Text(
                text = alarm.date,
                modifier = Modifier.padding(top = 2.dp),
                color = Color(193, 193, 193),
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            )
        }
    }

    private fun isAlarmEnabled(enabled: Int) : Boolean {
        return enabled == 1
    }

    private fun setAlarmEnabled(checkedState: MutableState<Boolean>, alarm: Alarm, alarmDb: AlarmRoomDatabase) {
        if (checkedState.value) {
            alarm.enabled = 1
            setAlarm(this, alarm)
        }
        else {
            alarm.enabled = 0
            cancelAlarm()
        }
        Thread {
            alarmDb.getDao().updateAlarm(alarm)
        }.start()
    }

    private fun setAlarm(context: Context, alarm: Alarm) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val hour = alarm.wakeTime.split(":")[0].toInt()
        val minute = alarm.wakeTime.split(":")[1].toInt()

        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        Toast.makeText(context, "Будильник установлен", Toast.LENGTH_SHORT).show()
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)
    }
}