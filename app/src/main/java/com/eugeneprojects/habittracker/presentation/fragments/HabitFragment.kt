package com.eugeneprojects.habittracker.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.R
import com.eugeneprojects.habittracker.databinding.FragmentHabitBinding
import com.eugeneprojects.habittracker.models.Habit
import com.eugeneprojects.habittracker.models.HabitPriority
import com.eugeneprojects.habittracker.models.HabitType
import com.eugeneprojects.habittracker.models.HabitsService
import com.eugeneprojects.habittracker.presentation.HabitListActivity
import java.lang.Exception

class HabitFragment : Fragment() {

    private var binding: FragmentHabitBinding? = null
    private lateinit var spinnerArrayAdapter: ArrayAdapter<HabitPriority>

    private val habitsService: HabitsService
        get() = (requireContext() as App).habitsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitBinding.inflate(inflater)
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
        spinnerArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, HabitPriority.entries).also {
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
            HabitType.NEUTRAL -> binding?.radioNeutral?.isChecked = true
            HabitType.GOOD -> binding?.radioGood?.isChecked = true
            HabitType.BAD -> binding?.radioBad?.isChecked = true
        }
        binding?.spinHabitPriority?.setSelection(spinnerArrayAdapter.getPosition(habit.habitPriority))
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
        val intent = Intent(this, HabitListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        saveHabit()
    }

    private fun onUpdateHabitPressed() {
        val intent = Intent(this, HabitListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        updateHabit()
    }

    private fun updateHabit() {
        val id = habit.id
        habitsService.updateHabit(
            id,
            binding?.edHabitName?.text.toString(),
            binding?.edHabitDescription?.text.toString(),
            spinnerArrayAdapter.getItem(binding?.spinHabitPriority?.selectedItemId!!.toInt())!!,
            when (binding?.rgHabitType?.findViewById<RadioButton>(binding?.rgHabitType!!.checkedRadioButtonId)) {
                binding?.radioNeutral -> HabitType.NEUTRAL
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
            spinnerArrayAdapter.getItem(binding?.spinHabitPriority?.selectedItemId!!.toInt())!!,
            when (binding?.rgHabitType?.findViewById<RadioButton>(binding?.rgHabitType!!.checkedRadioButtonId)) {
                binding?.radioNeutral -> HabitType.NEUTRAL
                binding?.radioGood -> HabitType.GOOD
                binding?.radioBad -> HabitType.BAD
                else -> throw Exception()
            },
            binding?.edHabitCount?.text.toString(),
            binding?.edHabitRhythm?.text.toString()
        )
    }

}