package com.eugeneprojects.habittracker.models

typealias HabitsLustener = (habits: List<Habit>) -> Unit
class HabitsService {
    var habits = mutableListOf<Habit>()

    private val listeners = mutableSetOf<HabitsLustener>()

    fun getHabits(): List<Habit> {
        return habits
    }

    fun addHabit(id: Int,
                 habitName: String?,
                 habitDescription: String?,
                 habitPriority: String?,
                 habitType: String?,
                 habitCount: String?,
                 habitRhythm: String?) {

        habits.add(Habit(id,habitName, habitDescription, habitPriority, habitType, habitCount, habitRhythm))
        notifyChanges()

    }

    fun addListener(listener: HabitsLustener) {
        listeners.add(listener)
        listener.invoke(habits)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(habits) }
    }

}

