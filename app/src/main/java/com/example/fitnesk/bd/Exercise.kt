package com.example.fitnesk.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    val name: String,
    val muscleGroup: String,
    val targetMuscles: String,
    val bodyPart: String,
    val calories: Int
){

    // Функция для получения информации об упражнении в виде строки
    fun getExerciseInfo(): String {
        return "Упражнение: $name\nГруппа мышц: $muscleGroup\nЦелевые мышцы: $targetMuscles\nЧасть тела: $bodyPart\nКалории: $calories"
    }
}