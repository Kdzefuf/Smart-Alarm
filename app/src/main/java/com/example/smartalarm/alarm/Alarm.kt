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
//) {
//
//    constructor() {}
//
//    constructor(id: Int, wakeTime: String, bedTime: String, date: String, repeatCount: Int, melody: Int, label: String, enabled: Boolean) {
//        this.id = id
//        this.wakeTime = wakeTime
//        this.bedTime = bedTime
//        this.date = date
//        this.repeatCount = repeatCount
//        this.melody = melody
//        this.label = label
//        if (enabled) {
//            this.enabled = 1
//        }
//        else {
//            this.enabled = 0
//        }
//    }
//
//    constructor(wakeTime: String, bedTime: String, date: String, repeatCount: Int, melody: Int, label: String, enabled: Boolean) {
//        this.wakeTime = wakeTime
//        this.bedTime = bedTime
//        this.date = date
//        this.repeatCount = repeatCount
//        this.melody = melody
//        this.label = label
//        if (enabled) {
//            this.enabled = 1
//        }
//        else {
//            this.enabled = 0
//        }
//    }
//}