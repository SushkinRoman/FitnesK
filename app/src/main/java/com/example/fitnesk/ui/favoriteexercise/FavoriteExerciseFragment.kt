package com.example.fitnesk.ui.favoriteexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesk.databinding.FragmentFavoriteExerciseBinding

class FavoriteExerciseFragment : Fragment() {

    private var _binding: FragmentFavoriteExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(FavoriteExerciseViewModel::class.java)

        _binding = FragmentFavoriteExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFavoriteexercise
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Обработчик нажатия на ImageButton
        binding.imageButton4.setOnClickListener {
            requireActivity().finish() // Завершает текущую активность и возвращает на MainActivity3
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}