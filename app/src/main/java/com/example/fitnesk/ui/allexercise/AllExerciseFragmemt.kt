package com.example.fitnesk.ui.allexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesk.AppIni
import com.example.fitnesk.MyRecyclerViewAdapter
import com.example.fitnesk.R
import com.example.fitnesk.databinding.FragmentAllExerciseBinding

class AllExercise : Fragment() {
    private var binding: FragmentAllExerciseBinding? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllExerciseBinding.inflate(inflater, container, false)
        binding?.textAllexercise?.text = "Список упражнений"
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = mLayoutManager

        val db = AppIni.getAppInstance().getDatabase()
        val weatherDao = db.exerciseDao()
        val weathers = weatherDao.getAllExercises().sortedBy { it.name.lowercase() }
        val mAdapter = MyRecyclerViewAdapter(weathers, requireActivity())
        mRecyclerView.adapter = mAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}