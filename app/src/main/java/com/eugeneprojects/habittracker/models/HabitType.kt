package com.eugeneprojects.habittracker.models

import androidx.annotation.StringRes
import com.eugeneprojects.habittracker.R

enum class HabitType(@StringRes val stringId: Int) {
    NEUTRAL(R.string.radio_neutral),
    GOOD(R.string.radio_good),
    BAD(R.string.radio_bad)
}