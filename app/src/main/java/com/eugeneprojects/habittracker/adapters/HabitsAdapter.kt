package com.eugeneprojects.habittracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneprojects.habittracker.databinding.ItemHabitLayoutBinding

class HabitsAdapter : RecyclerView.Adapter<HabitsAdapter.HabitsViewHolder>(){

    class HabitsViewHolder(private val binding: ItemHabitLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        return HabitsViewHolder(ItemHabitLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: HabitsViewHolder, position: Int) {

    }
}