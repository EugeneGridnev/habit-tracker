package com.eugeneprojects.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.eugeneprojects.habittracker.databinding.ActivityHabitBinding

class HabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setUpHabitPriority()
    }

    private fun setUpHabitPriority() {
        ArrayAdapter.createFromResource(
            this,
            R.array.habit_priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinHabitPriority.adapter = adapter
        }

        binding.spinHabitPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
    }
}