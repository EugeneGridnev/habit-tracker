package com.eugeneprojects.habittracker.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.R
import com.eugeneprojects.habittracker.adapters.HabitActionListener
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.databinding.FragmentHabitsListBinding
import com.eugeneprojects.habittracker.models.Habit
import com.eugeneprojects.habittracker.models.HabitsListener
import com.eugeneprojects.habittracker.models.HabitsService

class HabitsListFragment : Fragment() {

    private var binding: FragmentHabitsListBinding? = null
    private lateinit var habitsAdapter: HabitsAdapter

    private val habitsListener: HabitsListener = {
        habitsAdapter.habits = it
    }

    private val habitsService: HabitsService
        get() = (requireContext().applicationContext as App).habitsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitsListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabAddHabit?.setOnClickListener { onAddHabitFABPressed() }

        setUpRecyclerView()
    }

    override fun onDestroyView() {
        binding = null
        habitsService.removeListener(habitsListener)
        super.onDestroyView()
    }

    private fun setUpRecyclerView() {
        //TODO: юзать лямбды
        habitsAdapter = HabitsAdapter(object : HabitActionListener {
            override fun onHabitClick(habit: Habit) {
                findNavController().navigate(
                    R.id.action_habitsListFragment_to_habitFragment,
                    bundleOf(HabitFragment.ARGS_HABIT to habit)
                )
            }

        })
        binding?.rvHabitsList?.apply {
            adapter = habitsAdapter
        }
        habitsService.addListener (habitsListener)
    }

    private fun onAddHabitFABPressed() {
        findNavController().navigate(R.id.action_habitsListFragment_to_habitFragment)
    }
}