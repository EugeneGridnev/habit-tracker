package com.eugeneprojects.habittracker.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.databinding.FragmentHabitBinding
import com.eugeneprojects.habittracker.models.Habit
import com.eugeneprojects.habittracker.models.HabitPriority
import com.eugeneprojects.habittracker.models.HabitType
import com.eugeneprojects.habittracker.models.HabitsService
import java.lang.Exception

class HabitFragment : Fragment() {

    private var binding: FragmentHabitBinding? = null
    private lateinit var habit: Habit
    private lateinit var spinnerArrayAdapter: ArrayAdapter<HabitPriorityHolder>

    private val habitsService: HabitsService
        get() = (requireContext().applicationContext as App).habitsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitBinding.inflate(inflater)

        habit = arguments?.getParcelable(ARGS_HABIT)?: Habit.DEFAULT

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setUpHabitPriority() {
        val priorityItems = HabitPriority.entries.map {
            HabitPriorityHolder(it, requireContext().getText(it.stringId).toString())
        }.toTypedArray()

        spinnerArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorityItems).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.spinHabitPriority?.adapter = adapter
        }

        binding?.spinHabitPriority?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
    }

    //TODO: обрабатывать от состояния
    private fun setUpUI() {
        setUpHabitPriority()
        if (habit.id == Habit.DEFAULT_ID) {
            setUpSavedHabitButton()
        } else {
            setUpUIParams()
            setUpUpdateHabitButton()
        }

    }

    private fun setUpUIParams() {
        binding?.edHabitName?.setText(habit.habitName)
        binding?.edHabitDescription?.setText(habit.habitDescription)
        when (habit.habitType) {
            HabitType.GOOD -> binding?.radioGood?.isChecked = true
            HabitType.BAD -> binding?.radioBad?.isChecked = true
        }
        binding?.spinHabitPriority?.setSelection(spinnerArrayAdapter.getPosition(HabitPriorityHolder(habit.habitPriority)))
        binding?.edHabitCount?.setText(habit.habitCount)
        binding?.edHabitRhythm?.setText(habit.habitRhythm)
    }

    private fun setUpSavedHabitButton() {
        binding?.btnHabitSave?.setOnClickListener { onSaveHabitPressed() }
    }

    private fun setUpUpdateHabitButton() {
        binding?.btnHabitSave?.setOnClickListener { onUpdateHabitPressed() }
    }

    private fun onSaveHabitPressed() {
        saveHabit()
        findNavController().popBackStack()
    }

    private fun onUpdateHabitPressed() {
        updateHabit()
        findNavController().popBackStack()
    }

    private fun updateHabit() {
        val id = habit.id
        habitsService.updateHabit(
            id,
            binding?.edHabitName?.text.toString(),
            binding?.edHabitDescription?.text.toString(),
            spinnerArrayAdapter.getItem(binding?.spinHabitPriority?.selectedItemId!!.toInt())!!.priority,
            when (binding?.rgHabitType?.findViewById<RadioButton>(binding?.rgHabitType!!.checkedRadioButtonId)) {
                binding?.radioGood -> HabitType.GOOD
                binding?.radioBad -> HabitType.BAD
                else -> throw Exception()
            },
            binding?.edHabitCount?.text.toString(),
            binding?.edHabitRhythm?.text.toString()
        )
    }

    private fun saveHabit() {
        val id = habitsService.getHabits().size
        habitsService.addHabit(
            id,
            binding?.edHabitName?.text.toString(),
            binding?.edHabitDescription?.text.toString(),
            spinnerArrayAdapter.getItem(binding?.spinHabitPriority?.selectedItemId!!.toInt())!!.priority,
            when (binding?.rgHabitType?.findViewById<RadioButton>(binding?.rgHabitType!!.checkedRadioButtonId)) {
                binding?.radioGood -> HabitType.GOOD
                binding?.radioBad -> HabitType.BAD
                else -> throw Exception()
            },
            binding?.edHabitCount?.text.toString(),
            binding?.edHabitRhythm?.text.toString()
        )
    }

    private class HabitPriorityHolder(val priority: HabitPriority, val name: String = "") {
        override fun toString() = name

        override fun equals(other: Any?): Boolean {
            if (other is HabitPriorityHolder) {
                return priority == other.priority
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return priority.hashCode()
        }
    }

    companion object {
        const val ARGS_HABIT = "habit"
    }

}