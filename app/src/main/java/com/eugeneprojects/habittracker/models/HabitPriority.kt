package com.eugeneprojects.habittracker.models

import androidx.annotation.StringRes
import com.eugeneprojects.habittracker.R

enum class HabitPriority(@StringRes val stringId: Int) {
    NEUTRAL(R.string.habit_type_neutral),
    IMPORTANT(R.string.habit_type_important),
    UNIMPORTANT(R.string.habit_type_unimportant);
}