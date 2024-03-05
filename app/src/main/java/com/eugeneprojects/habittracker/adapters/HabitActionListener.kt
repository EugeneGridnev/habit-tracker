package com.eugeneprojects.habittracker.adapters

import com.eugeneprojects.habittracker.models.Habit

interface HabitActionListener {
    fun onHabitClick(habit: Habit)
}