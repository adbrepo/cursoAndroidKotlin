package com.utn.tabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.utn.tabs.R


class containerFragment : Fragment() {


    lateinit var v: View
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    private val titles =
        arrayOf("Movies", "Events", "Tickets")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_container, container, false)

        tabLayout = v.findViewById(R.id.tab_layout)

        viewPager = v.findViewById(R.id.view_pager)

        return v
    }

    override fun onStart() {
        super.onStart()

        viewPager.setAdapter(createCardAdapter())
       // viewPager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Tab1"
                    1 -> tab.text = "Tab2"
                    2 -> tab.text = "Tab3"
                    else -> tab.text = "undefined"
                }
            }).attach()
    }

    private fun createCardAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(requireActivity())
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {

            return when(position){
                0 -> fragment1()
                1 -> fragment2()
                2 -> fragment3()

                else -> fragment1()
            }
        }

        override fun getItemCount(): Int {
            return TAB_COUNT
        }

        companion object {
            private const val TAB_COUNT = 3
        }
    }

}