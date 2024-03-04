package com.eugeneprojects.habittracker.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Habit(
    val id: Int,
    val habitName: String?,
    val habitDescription: String?,
    val habitPriority: String?,
    val habitType: String?,
    val habitCount: String?,
    val habitRhythm: String?,
) : Parcelable {

}
