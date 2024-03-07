package com.eugeneprojects.habittracker.models

//TODO: передавать айдишник, чтобы выцепдять из ресурсов
enum class HabitType(val string: String) {
    NEUTRAL("Нейтральная"),
    GOOD("Хорошая"),
    BAD("Плохая")
}