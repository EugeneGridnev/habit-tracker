package com.eugeneprojects.habittracker.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Habit(
    val id: Int,
    var habitName: String?,
    var habitDescription: String?,
    var habitPriority: String?,
    var habitType: String?,
    var habitCount: String?,
    var habitRhythm: String?,
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT: Habit
            get() = Habit(
                id = this.DEFAULT_ID,
                habitName = "",
                habitDescription = "",
                habitPriority = "Нейтрально",
                habitType = "Нейтральная",
                habitCount = "",
                habitRhythm = ""
            )
        const val DEFAULT_ID: Int = -1
    }
}
