package com.example.testappdevandroid

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(activity:FragmentActivity, private val tabCount:Int,var listResults: Results) : FragmentStateAdapter(activity){
    private val mTabView = ArrayList<View>()
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> HomeFragment(listResults)
            1 -> ListFragment(listResults)
            2 -> NewsFragment()
            else -> HomeFragment(listResults)
        }
    }
    fun getTabView(position: Int): View? {
        return mTabView[position]
    }
    fun setTab(position: Int, view: View?) {
        if (view != null) {
            mTabView[position] = view
        }
    }
}