package com.example.fitnesk

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.room.Room
import com.example.fitnesk.bd.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppIni : Application() {
    companion object {
        lateinit var instance: AppIni
            private set

        fun getAppInstance(): AppIni {
            return instance
        }
    }

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Удаляем предыдущую базу данных, если она существует
        deleteDatabase("database")

        // Инициализируем базу данных
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

        // Инициализация базы данных с упражнениями
        initializeDatabase()
    }

    fun getDatabase(): AppDatabase {
        return database
    }

    private fun initializeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            // Получаем ресурсы приложения
            val resources: Resources = resources

            // Извлекаем массивы строк из ресурсов для упражнений
            val exerciseNames = resources.getStringArray(R.array.exercise_names)
            val muscleGroups = resources.getStringArray(R.array.muscle_groups)
            val targetMuscles = resources.getStringArray(R.array.target_muscles)
            val bodyParts = resources.getStringArray(R.array.body_parts)
            val caloriesArray = resources.getStringArray(R.array.calories)

            if (exerciseNames.isNotEmpty()) {
                // Создаем список для хранения объектов Exercise
                val exercises = mutableListOf<Exercise>()

                // Заполняем список exercises, создавая объекты Exercise
                for (i in exerciseNames.indices) {
                    val exercise = Exercise(
                        name = exerciseNames[i],
                        muscleGroup = muscleGroups[i],
                        targetMuscles = targetMuscles[i],
                        bodyPart = bodyParts[i],
                        calories = caloriesArray[i].toInt() // Преобразуем строку в целое число
                    )
                    exercises.add(exercise)
                }

                // Вставляем все упражнения в базу данных
                database.exerciseDao().insert(exercises)

                Log.d("Database", "Inserted ${exercises.size} exercises.")
            } else {
                Log.d("Database", "No exercises found in resources.")
            }
        }
    }
}
