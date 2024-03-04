package com.eugeneprojects.habittracker.models

typealias HabitsListener = (habits: List<Habit>) -> Unit
class HabitsService {
    private var habits = mutableListOf<Habit>()

    private val listeners = mutableSetOf<HabitsListener>()

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

    fun addListener(listener: HabitsListener) {
        listeners.add(listener)
        listener.invoke(habits)
    }

    fun removeListener(listener: HabitsListener) {
        listeners.remove(listener)
        listener.invoke(habits)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(habits) }
    }

}

