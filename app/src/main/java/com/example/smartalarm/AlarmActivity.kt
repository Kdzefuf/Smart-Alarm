package com.example.smartalarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartalarm.ui.theme.SmartAlarmTheme
import java.time.LocalTime
import java.util.Calendar

class AlarmActivity : ComponentActivity() {
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmTime: LocalTime
    private lateinit var mediaPlayer: MediaPlayer

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(this, R.raw.one)

        setContent {
            val repeatCheckedState =  remember { mutableStateOf(false) }
            val volumeUpCheckedState = remember { mutableStateOf(true) }
            val repeatText = "5 минут, 3 раза"
            val alarmName = remember{mutableStateOf("Название будильника")}
            val repeatIntent = Intent(this, RepeatActivity::class.java)

            SmartAlarmTheme {
                Column(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()) {
                    var context = LocalContext.current
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Установка будильника",
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
                    SetWheelTimePicker(
                        modifier = Modifier.fillMaxWidth(),
                        textColor = Color.White
                    ) {
                            snappedTime ->  alarmTime = snappedTime
                    }
                    Column(modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 36.dp)
                        .background(Color(42, 44, 62), shape = RoundedCornerShape(20.dp))
                        .padding(start = 20.dp, top = 20.dp, end = 21.dp, bottom = 21.dp)
                        .fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Завтра - пн, 25 мар.",
                                modifier = Modifier.fillMaxWidth(0.94f),
                                color = Color.White,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                            Image(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "Календарь",
                                modifier = Modifier
                                    .height(19.dp)
                                    .width(19.dp),
                                alignment = Alignment.CenterEnd
                            )
                        }
                        Row(Modifier.padding(start = 6.dp, top = 12.dp, end = 7.dp)) {
                            Text(text = "Пн",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(text = "Вт",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(text = "Ср",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(text = "Чт",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(
                                text = "Пт",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                            )
                            Text(text = "Сб",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color(119, 106, 166),
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(text = "Вс",
                                modifier = Modifier.width(25.dp),
                                color = Color(119, 106, 166),
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left
                            )
                        }
                        BasicTextField(
                            value = alarmName.value,
                            onValueChange = {alarmName.value = it},
                            modifier = Modifier.padding(start = 6.dp, top = 20.dp, end = 7.dp),
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            ),
                            singleLine = true
                        )
                        Box(modifier = Modifier
                            .padding(start = 6.dp, end = 7.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.White)
                        )
                        Row(modifier = Modifier.padding(start = 6.dp, top = 5.dp, end = 7.dp)) {
                            Column(modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .weight(1f)) {
                                Text(text = "Повтор",
                                    color = Color.White,
                                    fontWeight = FontWeight(400),
                                    fontSize = 16.sp
                                )
                                Text(text = repeatText,
                                    color = Color(119, 106, 166),
                                    fontWeight = FontWeight(400),
                                    fontSize = 11.sp,
                                    letterSpacing = TextUnit(0.8f, TextUnitType.Sp)
                                )
                            }
                            CreateSwitch(checkedState = repeatCheckedState, intent = repeatIntent)
                        }
                        Box(modifier = Modifier
                            .padding(start = 6.dp, top = 3.dp, end = 7.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(119, 106, 166))
                        )
                        Row(modifier = Modifier.padding(start = 6.dp, top = 10.dp, end = 7.dp)) {
                            Text(text = "Постепенное увеличение            громкости будильника",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(400),
                                fontSize = 16.sp
                            )
                            Switch (
                                checked = volumeUpCheckedState.value,
                                onCheckedChange = {
                                    volumeUpCheckedState.value = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFFFFFFFF),
                                    checkedTrackColor = Color(0xFF776AA6),
                                    uncheckedThumbColor = Color(0xFFFFFFFF),
                                    uncheckedTrackColor = Color(0xFF000000)
                                )
                            )
                        }
                        Box(modifier = Modifier
                            .padding(start = 6.dp, top = 3.dp, end = 7.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(119, 106, 166))
                        )
                        Row(modifier = Modifier.padding(start = 6.dp)) {
                            Text(text = "Выбор мелодии",
                                modifier = Modifier
                                    .padding(top = 14.dp)
                                    .fillMaxWidth()
                                    .weight(1f),
                                color = Color.White,
                                fontWeight = FontWeight(400),
                                fontSize = 16.sp
                            )
                            Button(onClick = {
                                val setMusicIntent = Intent(context, AlarmMusicActivity::class.java)
                                startActivity(setMusicIntent)
                            },
                                modifier = Modifier.padding(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                )) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = "Выбор мелодии",
                                    modifier = Modifier
                                        .padding(top = 2.dp)
                                        .height(19.dp)
                                        .width(19.dp),
                                    alignment = Alignment.CenterEnd
                                )
                            }
                        }
                    }
                    Button(onClick = { setAlarm(context) },
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(119, 106, 166)),
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .wrapContentWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Установить",
                            modifier = Modifier.padding(horizontal = 90.dp, vertical = 6.dp),
                            color = Color.White,
                            fontWeight = FontWeight(600),
                            fontSize = 16.sp
                        )
                    }
                    TextButton(onClick = { cancelAlarm() },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Отмена",
                            color = Color.White,
                            fontWeight = FontWeight(400),
                            fontSize = 16.sp)
                    }
                }
            }
        }
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val mainActivity = Intent(this, MainActivity::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(this, "Alarm cancelled", Toast.LENGTH_LONG).show()
        startActivity(mainActivity)
    }

    @Composable
    fun CreateSwitch(checkedState: MutableState<Boolean>, intent: Intent) {
        Switch (
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                startActivity(intent)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFFFFFF),
                checkedTrackColor = Color(0xFF776AA6),
                uncheckedThumbColor = Color(0xFFFFFFFF),
                uncheckedTrackColor = Color(0xFF000000)
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    private fun setAlarm(context: Context) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        calendar = Calendar.getInstance()

        calendar[Calendar.HOUR_OF_DAY] = alarmTime.hour
        calendar[Calendar.MINUTE] = alarmTime.minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        Toast.makeText(context, "Alarm set Succesfully", Toast.LENGTH_SHORT).show()
        val setAlarmIntent = Intent(this,AlarmMenu::class.java)
        startActivity(setAlarmIntent)
    }
}