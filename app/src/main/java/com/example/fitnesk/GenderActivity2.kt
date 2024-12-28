package com.example.fitnesk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class GenderActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender2)

        // Находим кнопки по ID
        val maleButton: Button = findViewById(R.id.maleButton)
        val femaleButton: Button = findViewById(R.id.femaleButton)

        // Устанавливаем обработчики нажатий на кнопки
        maleButton.setOnClickListener {
            // Переход на MainActivity3 с передачей информации о поле
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("gender", "Мужчина") // Передаем "Мужчина"
            startActivity(intent)
        }

        femaleButton.setOnClickListener {
            // Переход на MainActivity3 с передачей информации о поле
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("gender", "Женщина") // Передаем "Женщина"
            startActivity(intent)
        }
    }
}