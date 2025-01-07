package com.example.fitnesk

import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var scenario: ActivityScenario<MainActivity5>

    @Before
    fun setUp() {
        // Запускаем MainActivity5 перед каждым тестом
        scenario = ActivityScenario.launch(MainActivity5::class.java)
    }

    @Test
    fun useAppContext() {
        // Получаем контекст приложения, которое тестируется.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.fitnesk", appContext.packageName)
    }

    @Test
    fun validateInputsTest1() {
        // Пример корректных данных
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val validWeightEnd = 65f
            val validHeight = 175
            val validAge = 25

            val weightStartEditText = EditText(activity).apply { setText(validWeightStart.toString()) }
            val weightEndEditText = EditText(activity).apply { setText(validWeightEnd.toString()) }
            val heightEditText = EditText(activity).apply { setText(validHeight.toString()) }
            val ageEditText = EditText(activity).apply { setText(validAge.toString()) }

            val result = activity.validateInputs(validWeightStart, validWeightEnd, validHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertNull(result) // Ожидаем, что ошибок не будет
        }
    }

    @Test
    fun validateInputsTest2() {
        // Некорректный возраст (слишком низкий)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val validWeightEnd = 65f
            val validHeight = 175
            val invalidAge = 5

            val weightStartEditText = EditText(activity)
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity).apply { setText(invalidAge.toString()) }

            val result = activity.validateInputs(validWeightStart, validWeightEnd, validHeight, invalidAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Возраст должен быть от 10 до 150 лет.", result)
        }
    }

    @Test
    fun validateInputsTest3() {
        // Некорректный возраст (слишком высокий)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val validWeightEnd = 65f
            val validHeight = 175
            val invalidAge = 160

            val weightStartEditText = EditText(activity)
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity).apply { setText(invalidAge.toString()) }

            val result = activity.validateInputs(validWeightStart, validWeightEnd, validHeight, invalidAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Возраст должен быть от 10 до 150 лет.", result)
        }
    }

    @Test
    fun validateInputsTest4() {
        // Некорректный рост (слишком низкий)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val validWeightEnd = 65f
            val invalidHeight = 100
            val validAge = 25

            val weightStartEditText = EditText(activity)
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity).apply { setText(invalidHeight.toString()) }
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(validWeightStart, validWeightEnd, invalidHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Рост должен быть от 120 см до 300 см.", result)
        }
    }

    @Test
    fun validateInputsTest5() {
        // Некорректный рост (слишком высокий)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val validWeightEnd = 65f
            val invalidHeight = 310
            val validAge = 25

            val weightStartEditText = EditText(activity)
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity).apply { setText(invalidHeight.toString()) }
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(validWeightStart, validWeightEnd, invalidHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Рост должен быть от 120 см до 300 см.", result)
        }
    }

    @Test
    fun validateInputsTest6() {
        // Некорректный начальный вес (слишком низкий)
        scenario.onActivity { activity ->
            val invalidWeightStart = 20f
            val validWeightEnd = 65f
            val validHeight = 175
            val validAge = 25

            val weightStartEditText = EditText(activity).apply { setText(invalidWeightStart.toString()) }
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(invalidWeightStart, validWeightEnd, validHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Начальный вес должен быть от 40 до 300 кг.", result)
        }
    }

    @Test
    fun validateInputsTest7() {
        // Некорректный начальный вес (слишком высокий)
        scenario.onActivity { activity ->
            val invalidWeightStart = 350f
            val validWeightEnd = 65f
            val validHeight = 175
            val validAge = 25

            val weightStartEditText = EditText(activity).apply { setText(invalidWeightStart.toString()) }
            val weightEndEditText = EditText(activity)
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(invalidWeightStart, validWeightEnd, validHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Начальный вес должен быть от 40 до 300 кг.", result)
        }
    }

    @Test
    fun validateInputsTest8() {
        // Некорректный конечный вес (меньше 40 кг)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val invalidWeightEnd = 39f // Конечный вес не должен быть меньше 40 кг
            val validHeight = 175
            val validAge = 25

            val weightStartEditText = EditText(activity).apply { setText(validWeightStart.toString()) }
            val weightEndEditText = EditText(activity).apply { setText(invalidWeightEnd.toString()) }
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(validWeightStart, invalidWeightEnd, validHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Конечный вес не может быть меньше 40 кг или больше начального.", result)
        }
    }

    @Test
    fun validateInputsTest9() {
        // Некорректный конечный вес (больше начального)
        scenario.onActivity { activity ->
            val validWeightStart = 70f
            val invalidWeightEnd = 80f // Конечный вес не должен быть больше начального
            val validHeight = 175
            val validAge = 25

            val weightStartEditText = EditText(activity).apply { setText(validWeightStart.toString()) }
            val weightEndEditText = EditText(activity).apply { setText(invalidWeightEnd.toString()) }
            val heightEditText = EditText(activity)
            val ageEditText = EditText(activity)

            val result = activity.validateInputs(validWeightStart, invalidWeightEnd, validHeight, validAge,
                weightStartEditText, weightEndEditText, heightEditText, ageEditText)

            assertEquals("Ошибка: Конечный вес не может быть меньше 40 кг или больше начального.", result)
        }
    }

    @Test
    fun calculateBMRTestMale1() {
        // Проверяем корректность расчета BMR для мужчины
        scenario.onActivity { activity ->
            val genderMale = "Мужчина"
            val weight = 70f
            val height = 175
            val age = 25

            // Ожидаемое значение BMR для мужчины
            val expectedBMR = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)
            val actualBMR = activity.calculateBMR(genderMale, weight, height, age)
            assertEquals(expectedBMR, actualBMR, 0.01)
        }
    }

    @Test
    fun calculateBMRTestMale2() {
        // Проверяем корректность расчета BMR для другого мужчины
        scenario.onActivity { activity ->
            val genderMale = "Мужчина"
            val weight = 80f
            val height = 180
            val age = 30

            // Ожидаемое значение BMR для мужчины
            val expectedBMR = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)
            val actualBMR = activity.calculateBMR(genderMale, weight, height, age)
            assertEquals(expectedBMR, actualBMR, 0.01)
        }
    }

    @Test
    fun calculateBMRTestFemale1() {
        // Проверяем корректность расчета BMR для женщины
        scenario.onActivity { activity ->
            val genderFemale = "Женщина"
            val weight = 70f
            val height = 175
            val age = 25

            // Ожидаемое значение BMR для женщины
            val expectedBMR = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)
            val actualBMR = activity.calculateBMR(genderFemale, weight, height, age)
            assertEquals(expectedBMR, actualBMR, 0.01)
        }
    }

    @Test
    fun calculateBMRTestFemale2() {
        // Проверяем корректность расчета BMR для другой женщины
        scenario.onActivity { activity ->
            val genderFemale = "Женщина"
            val weight = 60f
            val height = 165
            val age = 28

            // Ожидаемое значение BMR для женщины
            val expectedBMR = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)
            val actualBMR = activity.calculateBMR(genderFemale, weight, height, age)
            assertEquals(expectedBMR, actualBMR, 0.01)
        }
    }
}
