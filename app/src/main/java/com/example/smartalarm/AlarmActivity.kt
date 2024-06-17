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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ButtonDefaults.buttonColors
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
import com.example.smartalarm.alarm.Alarm
import com.example.smartalarm.alarm.AlarmRoomDatabase
import com.example.smartalarm.ui.theme.SmartAlarmTheme
import java.time.LocalTime
import java.util.Calendar

class AlarmActivity : ComponentActivity() {
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmTime: LocalTime
    private lateinit var mediaPlayer: MediaPlayer
    private var alarmDayText: String = "Кажд. "
    private var dateText : String = ""
    private val repeatCount = RepeatActivity.getRepeatCount()
    private val repeatMinute = RepeatActivity.getRepeatMinutes()
    private var repeatCountText = ""
    private var repeatMinuteText = ""
    private val alarmMusic = AlarmMusicActivity.getAlarmSound()
    private val alarmMusicText = AlarmMusicActivity.getAlarmSoundText()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AlarmRoomDatabase.getInstance(this)

        val alarm = Alarm(enabled = 1)

        mediaPlayer = MediaPlayer.create(this, alarmMusic)
        calendar = Calendar.getInstance()

        val daysOfWeek = mapOf(1 to "вс", 2 to "пн", 3 to "вт", 4 to "ср",
            5 to "чт", 6 to "пт", 7 to "сб")
        val months = mapOf(0 to "янв.", 1 to "фев.", 2 to "мар.", 3 to "апр.", 4 to "мая",
            5 to "июня", 6 to "июля", 7 to "авг.", 8 to "сен.", 9 to "окт.", 10 to "ноя.",
            11 to "дек.")

        dateText = "Завтра - ${daysOfWeek[getTomorrowDate(calendar)]}, ${calendar[Calendar.DAY_OF_MONTH] + 1} ${months[calendar[Calendar.MONTH]]}"

        setContent {
            val repeatCheckedState =  remember { mutableStateOf(false) }
            val volumeUpCheckedState = remember { mutableStateOf(true) }


            isAlarmRepeat(repeatCheckedState)
            val alarmName = remember{mutableStateOf("Название будильника")}
            val repeatIntent = Intent(this, RepeatActivity::class.java)

            val mondayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val tuesdayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val wednesdayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val thursdayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val fridayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val saturdayButtonColor = remember { mutableStateOf(Color.Transparent) }
            val sundayButtonColor = remember { mutableStateOf(Color.Transparent) }

            val isMondayAlarm = remember { mutableStateOf(false) }
            val isTuesdayAlarm = remember { mutableStateOf(false) }
            val isWednesdayAlarm = remember { mutableStateOf(false) }
            val isThursdayAlarm = remember { mutableStateOf(false) }
            val isFridayAlarm = remember { mutableStateOf(false) }
            val isSaturdayAlarm = remember { mutableStateOf(false) }
            val isSundayAlarm = remember { mutableStateOf(false) }

            val saturdayTextColor = remember { mutableStateOf(Color(119, 106, 166)) }
            val sundayTextColor = remember { mutableStateOf(Color(119, 106, 166)) }

            SmartAlarmTheme {
                Column(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()) {
                    val context = LocalContext.current
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
                        alarm.wakeTime = alarmTime.toString()
                        alarm.bedTime = (alarmTime.hour - 8).toString() + ":" + alarmTime.minute
                    }
                    Column(modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 36.dp)
                        .background(Color(42, 44, 62), shape = RoundedCornerShape(20.dp))
                        .padding(start = 20.dp, top = 20.dp, end = 21.dp, bottom = 21.dp)
                        .fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = setDateText(),
                                modifier = Modifier.fillMaxWidth(0.94f),
                                color = Color.White,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                            Button(onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(60.dp),
                                colors = buttonColors(containerColor = Color.Transparent),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = "Календарь",
                                    modifier = Modifier
                                        .height(19.dp)
                                        .width(19.dp),
                                    alignment = Alignment.Center
                                )
                            }
                        }
                        Row(
                            Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth()) {
                            Button(onClick = {
                                    setDayOfWeek(mondayButtonColor, isMondayAlarm, "Пн. ")
                                    alarm.date = setDateText()
                                },
                                shape = RoundedCornerShape(60.dp),
                                colors = buttonColors(containerColor = mondayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Пн",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(tuesdayButtonColor, isTuesdayAlarm, "Вт. ")
                                    alarm.date = setDateText()
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = tuesdayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Вт",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(wednesdayButtonColor, isWednesdayAlarm, "Ср. ")
                                    alarm.date = setDateText()
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = wednesdayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Ср",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(thursdayButtonColor, isThursdayAlarm, "Чт. ")
                                    alarm.date = setDateText()
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = thursdayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Чт",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(fridayButtonColor, isFridayAlarm, "Пт. ")
                                    alarm.date = setDateText()
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = fridayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Пт",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(saturdayButtonColor, isSaturdayAlarm, "Сб. ")
                                    alarm.date = setDateText()
                                    if (saturdayButtonColor.value == Color.Transparent) {
                                        saturdayTextColor.value = Color(119, 106, 166)
                                    }
                                    else {
                                        saturdayTextColor.value = Color.White
                                    }
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = saturdayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Сб",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = saturdayTextColor.value,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(onClick = {
                                    setDayOfWeek(sundayButtonColor, isSundayAlarm, "Вс. ")
                                    alarm.date = setDateText()
                                    if (sundayButtonColor.value == Color.Transparent) {
                                        sundayTextColor.value = Color(119, 106, 166)
                                    }
                                    else {
                                        sundayTextColor.value = Color.White
                                    }
                                },
                                shape = RoundedCornerShape(40.dp),
                                colors = buttonColors(containerColor = sundayButtonColor.value),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .width(27.dp)
                                    .height(27.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "Вс",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = sundayTextColor.value,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        BasicTextField(
                            value = alarmName.value,
                            onValueChange = {alarmName.value = it},
                            modifier = Modifier.padding(start = 6.dp, top = 10.dp, end = 7.dp),
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
                                getRepeatCountText()
                                Text(text = "Повтор",
                                    color = Color.White,
                                    fontWeight = FontWeight(400),
                                    fontSize = 16.sp
                                )
                                Text(text = repeatMinuteText + repeatCountText,
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
                            Text(text = getAlarmMusicText(),
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
                                colors = buttonColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
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
                    Button(onClick = {
                        setAlarm(context)
                        alarm.label = alarmName.value
                        if (alarm.date == "") {
                            alarm.date = dateText
                        }
                        if (repeatCheckedState.value) {
                            getRepeatCountText()
                            alarm.repeatCount = repeatCount
                        }
                        else {
                            alarm.repeatCount = 0
                        }
                        alarm.melody = alarmMusic
                        Thread {
                            db.getDao().addAlarm(alarm = alarm)
                        }.start()
                                     },
                        shape = RoundedCornerShape(40.dp),
                        colors = buttonColors(containerColor = Color(119, 106, 166)),
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

        startActivity(mainActivity)
    }

    @Composable
    fun CreateSwitch(checkedState: MutableState<Boolean>, intent: Intent) {
        Switch (
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                if (checkedState.value) {
                    startActivity(intent)
                }
                if (!checkedState.value) {
                    repeatCountText = ""
                    repeatMinuteText = ""
                }
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

        Toast.makeText(context, "Будильник установлен", Toast.LENGTH_SHORT).show()
        val setAlarmIntent = Intent(this,AlarmMenu::class.java)
        startActivity(setAlarmIntent)
    }

    private fun setDayOfWeek(dayOfWeekButtonColor: MutableState<Color>, isDayAlarm: MutableState<Boolean>, dayOfWeek: String) {
        if (dayOfWeekButtonColor.value == Color.Transparent) {
            dayOfWeekButtonColor.value = Color(119, 106, 166)
            isDayAlarm.value = true
            alarmDayText += dayOfWeek
        }
        else {
            dayOfWeekButtonColor.value = Color.Transparent
            isDayAlarm.value = false
            alarmDayText = alarmDayText.replace(dayOfWeek, "")
        }

    }

    private fun setDateText() : String {
        if (alarmDayText != "Кажд. ") {
            return alarmDayText
        }
        else {
            return dateText
        }
    }

    private fun getTomorrowDate(calendar: Calendar) : Int {
        return if (calendar[Calendar.DAY_OF_WEEK] == 7) {
            1
        } else {
            calendar[Calendar.DAY_OF_WEEK] + 1
        }
    }

    private fun isAlarmRepeat(repeatCheckedState: MutableState<Boolean>) {
        if (repeatCount != 0) {
            repeatCheckedState.value = true
        }
    }

    private fun getRepeatCountText() {
        if (repeatCount == 3) {
            repeatCountText = "10 минут, $repeatCount раза"
        }
        if (repeatCount == 5) {
            repeatCountText = "10 минут, $repeatCount раз"
        }
        if (repeatCount == 100) {
            repeatCountText = "10 минут, бесконечно раз"
        }
    }

    private fun getRepeatMinutesText() {
        repeatMinuteText = "$repeatMinute минут,"
    }

    private fun getAlarmMusicText() : String {
        if (alarmMusicText == "") {
            return "Выбор мелодии"
        }
        else {
            return alarmMusicText
        }
    }
}