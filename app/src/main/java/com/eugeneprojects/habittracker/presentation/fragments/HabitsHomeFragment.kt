package com.eugeneprojects.habittracker.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.eugeneprojects.habittracker.App
import com.eugeneprojects.habittracker.R
import com.eugeneprojects.habittracker.adapters.HabitsAdapter
import com.eugeneprojects.habittracker.adapters.ViewPagerAdapter
import com.eugeneprojects.habittracker.databinding.FragmentHabitsHomeBinding
import com.eugeneprojects.habittracker.models.HabitsListener
import com.eugeneprojects.habittracker.models.HabitsService
import com.google.android.material.tabs.TabLayoutMediator

class HabitsHomeFragment : Fragment() {

    private var binding: FragmentHabitsHomeBinding? = null
    private lateinit var habitsListsViewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitsHomeBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabAddHabit?.setOnClickListener { onAddHabitFABPressed() }

        setUpViewPager()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setUpViewPager() {

        val fragmentsList = arrayListOf(
            GoodHabitsListFragment(),
            BadHabitsListFragment()
        )

        val tabsNamesList = arrayListOf(
            requireContext().getText(R.string.tab_good_text),
            requireContext().getText(R.string.tab_bad_text)
        )

        habitsListsViewPagerAdapter = ViewPagerAdapter(
            fragmentsList,
            childFragmentManager,
            lifecycle
        )

        binding?.vpHabitsLists?.adapter = habitsListsViewPagerAdapter

        TabLayoutMediator(binding!!.tlHabitsLists, binding!!.vpHabitsLists) { tab, position ->
            tab.text = tabsNamesList[position]
        }.attach()

    }

    private fun onAddHabitFABPressed() {
        findNavController().navigate(R.id.action_habitsListFragment_to_habitFragment)
    }
}