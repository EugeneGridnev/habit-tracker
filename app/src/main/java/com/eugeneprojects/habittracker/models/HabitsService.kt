package com.eugeneprojects.habittracker.models

typealias HabitsListener = (habits: List<Habit>) -> Unit

class HabitsService {
    private var habits = mutableListOf<Habit>()

    private val listeners = mutableSetOf<HabitsListener>()

    fun getHabits(): List<Habit> {
        return habits
    }

    fun addHabit(
        id: Int,
        habitName: String?,
        habitDescription: String?,
        habitPriority: HabitPriority,
        habitType: HabitType,
        habitCount: String?,
        habitRhythm: String?
    ) {

        habits.add(
            Habit(
                id,
                habitName,
                habitDescription,
                habitPriority,
                habitType,
                habitCount,
                habitRhythm
            )
        )
        notifyChanges()

    }

    //TODO: мапперы\фабрики
    fun updateHabit(
        id: Int,
        habitName: String?,
        habitDescription: String?,
        habitPriority: HabitPriority,
        habitType: HabitType,
        habitCount: String?,
        habitRhythm: String?
    ) {
        habits[id].habitName = habitName
        habits[id].habitDescription = habitDescription
        habits[id].habitPriority = habitPriority
        habits[id].habitType = habitType
        habits[id].habitCount = habitCount
        habits[id].habitRhythm = habitRhythm
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

