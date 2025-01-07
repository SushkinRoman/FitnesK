package com.example.fitnesk

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            // Сбрасываем стили ошибок
            resetErrorStyles(weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            // Проверяем, что все поля заполнены
            if (weightStartEditText.text.isEmpty() || weightEndEditText.text.isEmpty() || heightEditText.text.isEmpty() || ageEditText.text.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверяем, что введены только цифры
            if (!isNumeric(weightStartEditText.text.toString()) || !isNumeric(weightEndEditText.text.toString()) ||
                !isNumeric(heightEditText.text.toString()) || !isNumeric(ageEditText.text.toString())) {
                showError("Ошибка: Введите в поля только цифры.", resultTextView)
                return@setOnClickListener
            }

            // Преобразуем текстовые данные в числа
            val weightStart = weightStartEditText.text.toString().toFloat()
            val weightEnd = weightEndEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toInt()
            val age = ageEditText.text.toString().toInt()

            // Проверяем ограничения
            val errorMessage = validateInputs(weightStart, weightEnd, height, age, weightStartEditText, weightEndEditText, heightEditText, ageEditText)
            if (errorMessage != null) {
                showError(errorMessage, resultTextView)
                return@setOnClickListener
            }

            // Рассчитываем количество калорий в день
            val bmr = calculateBMR(gender, weightStart, height, age)
            val caloriesToLoseWeight = (weightStart - weightEnd) * 7700 // 1 кг ~ 7700 калорий
            val daysToLoseWeight = (caloriesToLoseWeight / 1100).toInt() // Безопасная потеря калорий

            // Формируем результат
            resultTextView.text = "БМП: $bmr калорий/день\n" +
                    "Для достижения конечного веса потребуется: $daysToLoseWeight дней\n" +
                    "Безопасно терять до 1100 калорий в день."
            resultTextView.setTextColor(Color.BLACK)
        }

        // Обработчик нажатия на кнопку "Назад"
        backButton.setOnClickListener {
            finish() // Завершает текущую активность и возвращает на предыдущую
        }
    }

    // Функция для расчета базового метаболического обмена (BMR)
    fun calculateBMR(gender: String?, weight: Float, height: Int, age: Int): Double {
        return if (gender == "Мужчина") {
            66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)
        } else {
            655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)
        }
    }

    // Функция для проверки, является ли строка числом
    private fun isNumeric(str: String): Boolean {
        return str.all { it.isDigit() }
    }

    // Функция для валидации введенных данных
    fun validateInputs(weightStart: Float, weightEnd: Float, height: Int, age: Int,
                       weightStartEditText: EditText, weightEndEditText: EditText,
                       heightEditText: EditText, ageEditText: EditText): String? {
        // Проверяем возраст
        if (age < 10 || age > 150) {
            ageEditText.setBackgroundColor(Color.RED)
            return "Ошибка: Возраст должен быть от 10 до 150 лет."
        }

        // Проверяем рост
        if (height < 120 || height > 300) {
            heightEditText.setBackgroundColor(Color.RED)
            return "Ошибка: Рост должен быть от 120 см до 300 см."
        }

        // Проверяем начальный вес
        if (weightStart < 40 || weightStart > 300) {
            weightStartEditText.setBackgroundColor(Color.RED)
            return "Ошибка: Начальный вес должен быть от 40 до 300 кг."
        }

        // Проверяем конечный вес
        if (weightEnd < 40 || weightEnd > weightStart) {
            weightStartEditText.setBackgroundColor(Color.RED)
            weightEndEditText.setBackgroundColor(Color.RED)
            return "Ошибка: Конечный вес не может быть меньше 40 кг или больше начального."
        }

        // Если все проверки пройдены, возвращаем null
        return null
    }
    // Функция для отображения ошибки
    private fun showError(message: String, resultTextView: TextView) {
        resultTextView.text = message
        resultTextView.setTextColor(Color.RED)
    }

    // Сброс стилей ошибок
    private fun resetErrorStyles(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.setBackgroundColor(Color.WHITE)
        }
    }
}
