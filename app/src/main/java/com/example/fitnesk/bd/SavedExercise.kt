package com.example.fitnesk.bd
import androidx.room.Entity

@Entity(tableName = "saved_exercises", primaryKeys = ["exerciseId"])
data class SavedExercise(
    val exerciseId: Long
)