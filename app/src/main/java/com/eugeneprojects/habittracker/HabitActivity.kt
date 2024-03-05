package com.eugeneprojects.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.eugeneprojects.habittracker.HabitListActivity.Companion.HABIT_KEY
import com.eugeneprojects.habittracker.databinding.ActivityHabitBinding
import com.eugeneprojects.habittracker.models.Habit
import com.eugeneprojects.habittracker.models.HabitsService

class HabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitBinding

    private lateinit var habit: Habit

    private val habitsService: HabitsService
        get() = (applicationContext as App).habitsService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitBinding.inflate(layoutInflater).also { setContentView(it.root) }

        habit = intent.getParcelableExtra(HABIT_KEY)?: Habit.DEFAULT

        setUpUI()
    }

    private fun setUpHabitPriority() {
        ArrayAdapter.createFromResource(
            this,
            R.array.habit_priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinHabitPriority.adapter = adapter
        }

        binding.spinHabitPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
    }

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
        binding.edHabitName.setText(habit.habitName)
        binding.edHabitDescription.setText(habit.habitDescription)
        binding.edHabitCount.setText(habit.habitCount)
        binding.edHabitRhythm.setText(habit.habitRhythm)
    }

    private fun setUpSavedHabitButton() {
        binding.btnHabitSave.setOnClickListener { onSaveHabitPressed() }
    }

    private fun setUpUpdateHabitButton() {
        binding.btnHabitSave.setOnClickListener { onUpdateHabitPressed() }
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
            binding.edHabitName.text.toString(),
            binding.edHabitDescription.text.toString(),
            binding.spinHabitPriority.selectedItem.toString(),
            binding.rgHabitType.findViewById<RadioButton>(binding.rgHabitType.checkedRadioButtonId).text.toString(),
            binding.edHabitCount.text.toString(),
            binding.edHabitRhythm.text.toString()
            )
    }

    private fun saveHabit() {
        val id = habitsService.getHabits().size
        habitsService.addHabit(
            id,
            binding.edHabitName.text.toString(),
            binding.edHabitDescription.text.toString(),
            binding.spinHabitPriority.selectedItem.toString(),
            binding.rgHabitType.findViewById<RadioButton>(binding.rgHabitType.checkedRadioButtonId).text.toString(),
            binding.edHabitCount.text.toString(),
            binding.edHabitRhythm.text.toString()
        )
    }

}