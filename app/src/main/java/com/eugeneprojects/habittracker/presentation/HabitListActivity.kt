package com.eugeneprojects.habittracker.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.adapters.HabitActionListener
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.databinding.ActivityHabitListBinding
import com.eugeneprojects.habittracker.models.Habit
import com.eugeneprojects.habittracker.models.HabitsListener
import com.eugeneprojects.habittracker.models.HabitsService

class HabitListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitListBinding

    private lateinit var habitsAdapter: HabitsAdapter

    private val habitsService: HabitsService
        get() = (applicationContext as App).habitsService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.fabAddHabit.setOnClickListener { onAddHabitFABPressed() }

        setUpRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        habitsService.removeListener(habitsListener)
    }

    private fun setUpRecyclerView() {
        //TODO: юзать лямбды
        habitsAdapter = HabitsAdapter(object : HabitActionListener {
            override fun onHabitClick(habit: Habit) {
                val intent = Intent(this@HabitListActivity, HabitActivity::class.java)
                intent.putExtra(HABIT_KEY, habit)
                startActivity(intent)
            }

        })
        binding.rvHabitsList.apply {
            adapter = habitsAdapter
            //TODO: можно пихать в лэйат, если не кастомный
            layoutManager = LinearLayoutManager(this@HabitListActivity)
        }
        habitsService.addListener (habitsListener)
    }

    private fun onAddHabitFABPressed() {
        val intent = Intent(this, HabitActivity::class.java)
        startActivity(intent)
    }
    //TODO: параметры выше
    private val habitsListener: HabitsListener = {
        habitsAdapter.habits = it
    }

    companion object {
        @JvmStatic val HABIT_KEY = "HABIT"
    }

}