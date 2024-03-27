package com.eugeneprojects.habittracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneprojects.habittracker.databinding.ItemHabitLayoutBinding
import com.eugeneprojects.habittracker.models.Habit

class HabitsAdapter(private val actionListener: (Habit) -> Unit) : RecyclerView.Adapter<HabitsAdapter.HabitsViewHolder>(), View.OnClickListener
{

    var habits: List<Habit> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class HabitsViewHolder(val binding: ItemHabitLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        val inflater = LayoutInflater.from((parent.context))
        val binding = ItemHabitLayoutBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return HabitsViewHolder(binding)
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: HabitsViewHolder, position: Int) {
        val habit = habits[position]
        with(holder.binding) {
            holder.itemView.tag = habit
            tvHabitName.text = habit.habitName
            tvHabitDescription.text = habit.habitDescription
            tvHabitPriority.text = habit.habitPriority.string
            tvHabitType.text = habit.habitType.string
            tvHabitCount.text = habit.habitCount
            tvHabitRhythm.text = habit.habitRhythm
        }
    }

    override fun onClick(v: View) {
        val habit = v.tag as Habit
        actionListener(habit)
    }
}