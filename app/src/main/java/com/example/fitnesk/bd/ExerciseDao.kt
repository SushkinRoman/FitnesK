package com.example.fitnesk.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercises: List<Exercise>)

    @Query("DELETE FROM exercises")
    fun deleteAllExercises()

    // Функция для поиска упражнения по имени
    @Query("SELECT * FROM exercises WHERE name = :exerciseName LIMIT 1")
    fun getExerciseByName(exerciseName: String): Exercise?

    // Функция для получения всех упражнений по группе мышц
    @Query("SELECT * FROM exercises WHERE muscleGroup = :muscleGroup")
    fun getExercisesByMuscleGroup(muscleGroup: String): List<Exercise>


}