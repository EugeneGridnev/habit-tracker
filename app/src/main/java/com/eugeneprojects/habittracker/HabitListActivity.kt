package com.eugeneprojects.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.databinding.ActivityHabitListBinding

class HabitListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitListBinding

    private lateinit var habitsAdapter: HabitsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.fabAddHabit.setOnClickListener { onAddHabitFABPressed() }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        habitsAdapter = HabitsAdapter()
        binding.rvHabitsList.apply {
            adapter = habitsAdapter
            layoutManager = LinearLayoutManager(this@HabitListActivity)
        }
    }

    private fun onAddHabitFABPressed() {
        val intent = Intent(this, HabitActivity::class.java)
        startActivity(intent)
    }

}