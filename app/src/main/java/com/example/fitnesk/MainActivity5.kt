// MainActivity5.kt
package com.example.fitnesk

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)

        // Получаем переданные данные о поле
        val gender = intent.getStringExtra("gender")

        // Находим элементы интерфейса
        val weightStartEditText: EditText = findViewById(R.id.editTextWeightStart)
        val weightEndEditText: EditText = findViewById(R.id.editTextWeightEnd)
        val heightEditText: EditText = findViewById(R.id.editTextHeight)
        val ageEditText: EditText = findViewById(R.id.editTextAge)
        val calculateButton: Button = findViewById(R.id.buttonCalculate)
        val resultTextView: TextView = findViewById(R.id.textViewResult)
        val backButton: ImageButton = findViewById(R.id.imageButton)

        // Обработчик нажатия на кнопку "Рассчитать"
        calculateButton.setOnClickListener {
            // Проверяем, что все поля заполнены
            if (weightStartEditText.text.isEmpty() || weightEndEditText.text.isEmpty() || heightEditText.text.isEmpty() || ageEditText.text.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Преобразуем текстовые данные в числа
            val weightStart = weightStartEditText.text.toString().toFloat()
            val weightEnd = weightEndEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toInt()
            val age = ageEditText.text.toString().toInt()

            // Рассчитываем количество калорий в день
            val bmr = calculateBMR(gender, weightStart, height, age)
            val caloriesToLoseWeight = (weightStart - weightEnd) * 7700 // 1 кг ~ 7700 калорий
            val daysToLoseWeight = (caloriesToLoseWeight / 1100).toInt() // Безопасная потеря калорий

            // Формируем результат
            resultTextView.text = "БМП: $bmr калорий/день\n" +
                    "Для достижения конечного веса потребуется: $daysToLoseWeight дней\n" +
                    "Безопасно терять до 1100 калорий в день."
            resultTextView.setTextColor(resources.getColor(R.color.black))
        }

        // Обработчик нажатия на кнопку "Назад"
        backButton.setOnClickListener {
            finish() // Завершает текущую активность и возвращает на предыдущую
        }
    }

    // Функция для расчета базового метаболического обмена (BMR)
    private fun calculateBMR(gender: String?, weight: Float, height: Int, age: Int): Double {
        return if (gender == "Мужчина") {
            66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)
        } else {
            655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)
        }
    }
}