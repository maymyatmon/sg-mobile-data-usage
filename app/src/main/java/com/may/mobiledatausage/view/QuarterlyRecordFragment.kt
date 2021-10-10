package com.may.mobiledatausage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.may.mobiledatausage.R
import com.may.mobiledatausage.adapter.PagerAdapter


private const val ARG_PARAM1 = "index"

class QuarterlyRecordFragment : Fragment() {
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            index = it.getInt(ARG_PARAM1)
        }
        Log.d("QuarterlyRecordFragment", "Clicked Index: $index")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quarterly_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        viewPager.adapter = fragmentManager?.let { PagerAdapter(it) }
        tabLayout.setupWithViewPager(viewPager)
        viewPager.setCurrentItem(index, true)
        viewPager.offscreenPageLimit = 1
    }
}