package com.example.fitnesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        // Получаем переданные данные
        val gender = intent.getStringExtra("gender")

        // Находим TextView по ID и устанавливаем текст в зависимости от пола
        val genderTextView: TextView = findViewById(R.id.textView4)
        genderTextView.text = gender // Устанавливаем текст "Мужчина" или "Женщина"

        // Устанавливаем цвет текста в зависимости от пола
        genderTextView.setTextColor(if (gender == "Мужчина") {
            resources.getColor(android.R.color.holo_blue_dark) // Темно-синий для мужчин
        } else {
            resources.getColor(android.R.color.holo_red_light) // Светло-красный для женщин
        })

        // Обработчик нажатия на ImageButton для возврата в GenderActivity2
        val backButton: ImageButton = findViewById(R.id.imageButton)
        backButton.setOnClickListener {
            finish() // Завершает текущую активность и возвращает на предыдущую
        }

        // Обработчик нажатия на кнопку "Войти в калькулятор"
        val calculatorButton: Button = findViewById(R.id.button5)
        calculatorButton.setOnClickListener {
            // Передаем данные о поле в MainActivity5
            val intent = Intent(this, MainActivity5::class.java)
            intent.putExtra("gender", gender)
            startActivity(intent)
        }

        // Обработчик нажатия на кнопку "Упражнения"
        val exercisesButton: Button = findViewById(R.id.button) // Находим кнопку "Упражнения"
        exercisesButton.setOnClickListener {
            // Переходим на ActivityMainUpr4
            val intent = Intent(this, MainActivityUpr4::class.java)
            startActivity(intent) // Запускаем новую активность
        }
        // Обработчик нажатия на кнопку "Упражнения"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}