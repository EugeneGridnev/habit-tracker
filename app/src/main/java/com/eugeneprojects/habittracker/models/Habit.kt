package com.eugeneprojects.habittracker.models

data class Habit(
    val id: Int,
    val habitName: String?,
    val habitDescription: String?,
    val habitPriority: String?,
    val habitType: String?,
    val habitCount: String?,
    val habitRhythm: String?,
)
