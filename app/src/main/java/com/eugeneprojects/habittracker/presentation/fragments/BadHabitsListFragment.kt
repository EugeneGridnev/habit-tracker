package com.eugeneprojects.habittracker.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.R
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.databinding.FragmentBadHabitsListBinding
import com.eugeneprojects.habittracker.models.HabitType
import com.eugeneprojects.habittracker.models.HabitsListener
import com.eugeneprojects.habittracker.models.HabitsService

class BadHabitsListFragment : Fragment() {

    private var binding: FragmentBadHabitsListBinding? = null
    private lateinit var habitsAdapter: HabitsAdapter

    private val habitsListener: HabitsListener = { it ->
        habitsAdapter.habits = it.filter { it.habitType == HabitType.BAD }
    }

    private val habitsService: HabitsService
        get() = (requireContext().applicationContext as App).habitsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBadHabitsListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    override fun onDestroyView() {
        binding = null
        habitsService.removeListener(habitsListener)
        super.onDestroyView()
    }

    private fun setUpRecyclerView() {

        habitsAdapter = HabitsAdapter {

            findNavController().navigate(
                R.id.action_habitsListFragment_to_habitFragment,
                bundleOf(HabitFragment.ARGS_HABIT to it)
            )
        }
        binding?.rvBadHabitsList?.apply {
            adapter = habitsAdapter
        }
        habitsService.addListener (habitsListener)
    }

}