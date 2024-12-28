package com.example.fitnesk

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Разрешаем режим "edge-to-edge"
        setContentView(R.layout.activity_main)

        // Устанавливаем отступы для системы
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Находим кнопку "Войти" по ID
        val loginButton: Button = findViewById(R.id.loginButton)

        // Устанавливаем обработчик нажатия на кнопку
        loginButton.setOnClickListener {
            // Переход на следующее активити (например, GenderActivity)
            val intent = Intent(this, GenderActivity2::class.java)
            startActivity(intent)
        }
    }
}