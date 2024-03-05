package com.eugeneprojects.habittracker.models

import com.eugeneprojects.habittracker.R

enum class HabitType(val string: String) {
    NEUTRAL(R.string.radio_neutral.toString()),
    GOOD(R.string.radio_good.toString()),
    BAD(R.string.radio_bad.toString())
}