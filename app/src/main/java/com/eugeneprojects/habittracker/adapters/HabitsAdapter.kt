package com.eugeneprojects.habittracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneprojects.habittracker.databinding.ItemHabitLayoutBinding
import com.eugeneprojects.habittracker.models.Habit

class HabitsAdapter : RecyclerView.Adapter<HabitsAdapter.HabitsViewHolder>(){

    var habits: List<Habit> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class HabitsViewHolder(val binding: ItemHabitLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        return HabitsViewHolder(ItemHabitLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: HabitsViewHolder, position: Int) {
        val habit = habits[position]
        with(holder.binding) {
            tvHabitName.text = habit.habitName
            tvHabitPriority.text= habit.habitPriority.toString()
            tvHabitType.text= habit.habitType.toString()
        }
    }
}