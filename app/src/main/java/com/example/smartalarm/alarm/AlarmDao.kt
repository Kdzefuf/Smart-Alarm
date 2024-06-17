package com.example.smartalarm.alarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    fun getAlarms(): Flow<List<Alarm>>

    @Query("SELECT * FROM alarms WHERE id = :id")
    fun getAlarm(id: Int): List<Alarm>

    @Insert
    fun addAlarm(alarm: Alarm)

    @Query("DELETE FROM alarms WHERE id = :id")
    fun deleteAlarm(id: Int)

    @Update
    fun updateAlarm(alarm: Alarm)
}