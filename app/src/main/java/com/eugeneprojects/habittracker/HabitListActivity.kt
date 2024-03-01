package com.eugeneprojects.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.databinding.ActivityHabitListBinding

class HabitListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitListBinding

    lateinit var habitsAdapter: HabitsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        habitsAdapter = HabitsAdapter()
        binding.rvHabitsList.apply {
            adapter = habitsAdapter
            layoutManager = LinearLayoutManager(this@HabitListActivity)
        }
    }

}