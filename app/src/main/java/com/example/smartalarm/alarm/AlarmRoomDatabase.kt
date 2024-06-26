package com.example.smartalarm.alarm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Alarm::class], version = 1)
abstract class AlarmRoomDatabase: RoomDatabase() {
    abstract fun getDao(): AlarmDao

    companion object {
        fun getInstance(context: Context) : AlarmRoomDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    AlarmRoomDatabase::class.java,
                    "smart_alarm.db"
                ).build()
        }
    }
}