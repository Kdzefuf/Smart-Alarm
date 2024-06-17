package com.example.smartalarm.alarm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "wake_time")
    var wakeTime: String = "",
    @ColumnInfo(name = "bed_time")
    var bedTime: String = "",
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "repeat_count")
    var repeatCount: Int = 0,
    @ColumnInfo(name = "melody")
    var melody: Int = 0,
    @ColumnInfo(name = "label")
    var label: String = "",
    @ColumnInfo(name = "enabled")
    var enabled: Int = 0
)