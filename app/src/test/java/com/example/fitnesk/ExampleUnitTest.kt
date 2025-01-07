package com.example.fitnesk

import android.graphics.Color
import android.widget.EditText
import org.junit.Test
import org.junit.Assert.*

/**
 * Пример локального юнит-теста, который выполняется на машине разработчика (хост).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCalculateBMR_Male() {
        // Проверяем BMR для мужчины
        val gender = "Мужчина"
        val weight = 70f
        val height = 175
        val age = 25
        val expectedBMR = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)

        val resultBMR = calculateBMR(gender, weight, height, age)
        assertEquals(expectedBMR, resultBMR, 0.01)
    }

    @Test
    fun testCalculateBMR_Female() {
        // Проверяем BMR для женщины
        val gender = "Женщина"
        val weight = 60f
        val height = 165
        val age = 30
        val expectedBMR = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)

        val resultBMR = calculateBMR(gender, weight, height, age)
        assertEquals(expectedBMR, resultBMR, 0.01)
    }

    @Test
    fun testValidateInputs_ValidInputs() {
        // Проверяем корректные входные данные
        val weightStart = 70f
        val weightEnd = 65f
        val height = 175
        val age = 25
        val result = validateInputs(weightStart, weightEnd, height, age, EditText(null), EditText(null), EditText(null), EditText(null))
        assertNull(result) // Ожидаем, что ошибок нет
    }

    @Test
    fun testValidateInputs_InvalidAge() {
        // Проверяем некорректный возраст
        val weightStart = 70f
        val weightEnd = 65f
        val height = 175
        val age = 5 // Неверный возраст
        val result = validateInputs(weightStart, weightEnd, height, age, EditText(null), EditText(null), EditText(null), EditText(null))
        assertEquals("Ошибка: Возраст должен быть от 10 до 150 лет.", result)
    }

    @Test
    fun testValidateInputs_InvalidHeight() {
        // Проверяем некорректный рост
        val weightStart = 70f
        val weightEnd = 65f
        val height = 50 // Неверный рост
        val age = 25
        val result = validateInputs(weightStart, weightEnd, height, age, EditText(null), EditText(null), EditText(null), EditText(null))
        assertEquals("Ошибка: Рост должен быть от 120 см до 300 см.", result)
    }

    @Test
    fun testValidateInputs_InvalidWeightStart() {
        // Проверяем некорректный начальный вес
        val weightStart = 30f // Неверный начальный вес
        val weightEnd = 65f
        val height = 175
        val age = 25
        val result = validateInputs(weightStart, weightEnd, height, age, EditText(null), EditText(null), EditText(null), EditText(null))
        assertEquals("Ошибка: Начальный вес должен быть от 40 до 300 кг.", result)
    }

    @Test
    fun testValidateInputs_InvalidWeightEnd() {
        // Проверяем некорректный конечный вес
        val weightStart = 70f
        val weightEnd = 75f // Неверный конечный вес
        val height = 175
        val age = 25
        val result = validateInputs(weightStart, weightEnd, height, age, EditText(null), EditText(null), EditText(null), EditText(null))
        assertEquals("Ошибка: Начальный вес не может быть меньше конечного веса.", result)
    }

    // Вспомогательные функции для тестирования
    private fun calculateBMR(gender: String?, weight: Float, height: Int, age: Int): Double {
        return if (gender == "Мужчина") {
            66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)
        } else {
            655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)
        }
    }

    private fun validateInputs(weightStart: Float, weightEnd: Float, height: Int, age: Int,
                               weightStartEditText: EditText, weightEndEditText: EditText,
                               heightEditText: EditText, ageEditText: EditText
    ): String? {
        return when {
            age < 10 || age > 150 -> {
                ageEditText.setBackgroundColor(Color.RED)
                "Ошибка: Возраст должен быть от 10 до 150 лет."
            }
            height < 120 || height > 300 -> {
                heightEditText.setBackgroundColor(Color.RED)
                "Ошибка: Рост должен быть от 120 см до 300 см."
            }
            weightStart < 40 || weightStart > 300 -> {
                weightStartEditText.setBackgroundColor(Color.RED)
                "Ошибка: Начальный вес должен быть от 40 до 300 кг."
            }
            weightEnd < 40 || weightEnd > weightStart -> {
                weightStartEditText.setBackgroundColor(Color.RED)
                weightEndEditText.setBackgroundColor(Color.RED)
                "Ошибка: Начальный вес не может быть меньше конечного веса."
            }
            else -> null // Все проверки пройдены
        }
    }
}
