package com.example.smartalarm

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AlarmMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val onAlarm1State =  remember { mutableStateOf(false) }
            val alarmTime1 = "05:30"
            val onAlarm2State =  remember { mutableStateOf(false) }
            val alarmTime2 = "09:15"
            val onAlarm3State =  remember { mutableStateOf(false) }
            val alarmTime3 = "12:00"

            Column(modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                var context = LocalContext.current
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
                                text = "Название",
                                modifier = Modifier.fillMaxWidth(0.94f),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                            Image(
                                painter = painterResource(id = R.drawable.cancel_alarm),
                                contentDescription = "cancel",
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(18.dp),
                                alignment = Alignment.CenterEnd
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = alarmTime1,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(top = 6.dp),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(500),
                                fontSize = 36.sp
                            )
                            CreateSwitch(checkedState = onAlarm1State)
                        }
                        Text(
                            text = "Дни недели",
                            modifier = Modifier.padding(top = 2.dp),
                            color = Color(193, 193, 193),
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
                            .background(Color(42, 44, 62), shape = RoundedCornerShape(20.dp))
                            .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 18.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Название",
                                modifier = Modifier.fillMaxWidth(0.94f),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                            Image(
                                painter = painterResource(id = R.drawable.cancel_alarm),
                                contentDescription = "cancel",
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(18.dp),
                                alignment = Alignment.CenterEnd
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = alarmTime2,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(top = 6.dp),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(500),
                                fontSize = 36.sp
                            )
                            CreateSwitch(checkedState = onAlarm2State)
                        }
                        Text(
                            text = "Дни недели",
                            modifier = Modifier.padding(top = 2.dp),
                            color = Color(193, 193, 193),
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
                            .background(Color(42, 44, 62), shape = RoundedCornerShape(20.dp))
                            .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 18.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Название",
                                modifier = Modifier.fillMaxWidth(0.94f),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Left
                            )
                            Image(
                                painter = painterResource(id = R.drawable.cancel_alarm),
                                contentDescription = "cancel",
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(18.dp),
                                alignment = Alignment.CenterEnd
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = alarmTime3,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(top = 6.dp),
                                color = Color(193, 193, 193),
                                fontWeight = FontWeight(500),
                                fontSize = 36.sp
                            )
                            CreateSwitch(checkedState = onAlarm3State)
                        }
                        Text(
                            text = "Дни недели",
                            modifier = Modifier.padding(top = 2.dp),
                            color = Color(193, 193, 193),
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp
                        )
                    }
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
    fun CreateSwitch(checkedState: MutableState<Boolean>) {
        Switch (
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
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
}