package com.example.fitnesk.ui.chosenexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitnesk.R
import com.example.fitnesk.databinding.FragmentChosenExerciseBinding

class ChosenExerciseFragment : Fragment() {

    private var _binding: FragmentChosenExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChosenExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Извлечение данных из Bundle
        val exerciseName = arguments?.getString("chosen exercise")
        val muscleGroup = arguments?.getString("muscleGroup")
        val targetMuscles = arguments?.getString("targetMuscles")
        val bodyPart = arguments?.getString("bodyPart")
        val calories = arguments?.getInt("calories")

        // Установка данных в соответствующие TextView
        binding.textChosenexercise.text = "$exerciseName" // Название упражнения
        binding.textView1.text = "Группа мышц: $muscleGroup"
        binding.textTemp.text = "Целевые мышцы: $targetMuscles"
        binding.textWind.text = "Часть тела: $bodyPart"
        binding.textCond.text = "Калории: $calories"

        // Обработчик нажатия на ImageButton
        binding.imageButton3.setOnClickListener {
            requireActivity().finish() // Завершает текущую активность и возвращает на MainActivity3
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}