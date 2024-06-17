package com.example.smartalarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle
import java.text.SimpleDateFormat
import java.util.Date


class StatsActivity : ComponentActivity() {

    lateinit var fallAsleepModel: AAChartModel
    lateinit var wakeUpModel: AAChartModel
    lateinit var sleepDurationModel: AAChartModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stats_activity)

        val fallAsleepView = findViewById<AAChartView>(R.id.fall_asleep_view)
        val wakeUpView = findViewById<AAChartView>(R.id.wake_up_view)
        val sleepDurationView = findViewById<AAChartView>(R.id.sleep_duration_view)

        fallAsleepModel = AAChartModel()
            .chartType(AAChartType.Column)
            .title("Время засыпания")
            .backgroundColor("#00000000")
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("")
                    .data(arrayOf(convertDateToLong("22:30") / 3600000, convertDateToLong("23:30") / 3600000, convertDateToLong("23:00") / 3600000, convertDateToLong("22:30") / 3600000, convertDateToLong("23:30") / 3600000, convertDateToLong("24:00") / 3600000, convertDateToLong("22:00") / 3600000))))
            .axesTextColor("#C1C1C1")
            .categories(arrayOf("пн", "вт", "ср", "чт", "пт", "сб", "вс"))
            .borderRadius(5)
            .colorsTheme(arrayOf("#776AA6"))
            .titleStyle(AAStyle().color("#C1C1C1").fontSize(16).fontFamily("montserrat").position("left"))
            .yAxisTitle("")
            .touchEventEnabled(false)
            .yAxisMax(24)
            .yAxisMin(21)
        fallAsleepView.aa_drawChartWithChartModel(fallAsleepModel)

        wakeUpModel = AAChartModel()
            .chartType(AAChartType.Column)
            .title("Время пробуждения")
            .backgroundColor("#00000000")
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("")
                    .data(arrayOf(convertDateToLong("06:00") / 3600000,
                        convertDateToLong("07:00") / 3600000,
                        convertDateToLong("07:00") / 3600000,
                        convertDateToLong("08:00") / 3600000,
                        convertDateToLong("07:00") / 3600000,
                        convertDateToLong("10:00") / 3600000,
                        convertDateToLong("10:00") / 3600000))))
            .axesTextColor("#C1C1C1")
            .categories(arrayOf("пн", "вт", "ср", "чт", "пт", "сб", "вс"))
            .borderRadius(5)
            .colorsTheme(arrayOf("#776AA6"))
            .titleStyle(AAStyle()
                .color("#C1C1C1")
                .fontSize(16)
                .fontFamily("montserrat")
                .position("left"))
            .yAxisTitle("")
            .touchEventEnabled(false)
            .yAxisMax(10)
            .yAxisMin(5)
        wakeUpView.aa_drawChartWithChartModel(wakeUpModel)

        sleepDurationModel = AAChartModel()
            .chartType(AAChartType.Column)
            .title("Время пробуждения")
            .backgroundColor("#00000000")
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("")
                    .data(arrayOf(6.0, 7.0, 7.5, 8.5, 7.5, 8.5, 9.0))))
            .axesTextColor("#C1C1C1")
            .categories(arrayOf("пн", "вт", "ср", "чт", "пт", "сб", "вс"))
            .borderRadius(5)
            .colorsTheme(arrayOf("#776AA6"))
            .titleStyle(AAStyle()
                .color("#C1C1C1")
                .fontSize(16)
                .fontFamily("montserrat")
                .position("left"))
            .yAxisTitle("")
            .touchEventEnabled(false)
            .yAxisMax(9)
            .yAxisMin(5)
        sleepDurationView.aa_drawChartWithChartModel(sleepDurationModel)

        val openDayStatsButton = findViewById<Button>(R.id.statsDayButton)
        openDayStatsButton.setOnClickListener {
            val openDayStatsIntent = Intent(this, StatsDayActivity::class.java)
            startActivity(openDayStatsIntent)
        }

        val openAlarmsButton = findViewById<Button>(R.id.alarm)
        openAlarmsButton.setOnClickListener {
            val openAlarmsIntent = Intent(this, AlarmMenu::class.java)
            startActivity(openAlarmsIntent)
        }

        val openMainButton = findViewById<Button>(R.id.tracker)
        openMainButton.setOnClickListener {
            val openMainIntent = Intent(this, MainActivity::class.java)
            startActivity(openMainIntent)
        }

        val openMeditationButton = findViewById<Button>(R.id.music)
        openMeditationButton.setOnClickListener {
            val openMeditationIntent = Intent(this, MeditationActivity::class.java)
            startActivity(openMeditationIntent)
        }

        val openSettingsButton = findViewById<Button>(R.id.settings)
        openSettingsButton.setOnClickListener {
            val openSettingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(openSettingsIntent)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long) : String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateToLong(date: String): Double {
        val df = SimpleDateFormat("HH:mm")
        return df.parse(date)!!.time.toDouble()
    }
}