package com.eugeneprojects.habittracker.models

enum class HabitPriority(val string: String) {
    NEUTRAL("Нейтральная"),
    IMPORTANT("Важная"),
    UNIMPORTANT("Неважная");

    override fun toString() = string
}