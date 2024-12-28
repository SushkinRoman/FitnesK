package com.example.fitnesk

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesk.bd.Exercise
import com.example.fitnesk.bd.ExerciseDao

class MyRecyclerViewAdapter(
    private val exerciseList: List<Exercise>, // Изменяем на Exercise
    private val context: Activity
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTextView: TextView = view.findViewById(R.id.my_recycler)
        lateinit var exercise: Exercise // Изменяем на Exercise

        init {
            mTextView.setOnClickListener {
                Log.i("Click", "on item click")
                val bundle = Bundle().apply {
                    putString("chosen exercise", mTextView.text.toString()) // Изменяем на "chosen exercise"
                    putString("muscleGroup", exercise.muscleGroup) // Добавляем группу мышц
                    putString("targetMuscles", exercise.targetMuscles) // Добавляем целевые мышцы
                    putString("bodyPart", exercise.bodyPart) // Добавляем часть тела
                    putInt("calories", exercise.calories) // Добавляем калории
                }
                Navigation.findNavController(it).navigate(R.id.action_nav_allexercise_to_nav_chosenexercise, bundle)
            }

            /*mTextView.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setCancelable(true)
                    .setIcon(R.drawable.ic_launcher_foreground) // Замените на ваш ресурс
                    .setMessage(mTextView.text.toString())
                    .setTitle("Добавление в избранное")
                    .setPositiveButton("В избранное") { _, _ ->
                        // Логика добавления в избранное (можно добавить сюда)
                    }
                    .setNeutralButton("Отмена") { dialog, _ ->
                        Log.i("AlertDialog", "on neutral button click")
                    }
                builder.create().show()
                true
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTextView.text = exerciseList[position].name // Изменяем на name
        holder.exercise = exerciseList[position]
    }

    override fun getItemCount(): Int = exerciseList.size
}
