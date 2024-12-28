package com.example.fitnesk

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesk.bd.Exercise
import com.example.fitnesk.bd.ExerciseDao
import com.example.fitnesk.bd.SavedExercise

@Database(entities = [Exercise::class,  SavedExercise::class, ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}