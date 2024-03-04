package com.eugeneprojects.habittracker

import android.app.Application
import com.eugeneprojects.habittracker.models.HabitsService

class App: Application() {
    val habitsService = HabitsService()
}