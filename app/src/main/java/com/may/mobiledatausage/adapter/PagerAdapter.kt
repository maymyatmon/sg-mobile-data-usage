package com.may.mobiledatausage.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.may.mobiledatausage.view.ContentFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val years = listOf("2005", "2006", "2007", "2008", "2009", "2010")

    override fun getCount(): Int {
        return years.size
    }

    override fun getItem(position: Int): Fragment {

        val contentFragment = ContentFragment()
        val bundle = Bundle()
        bundle.putString("year", years.get(position))
        contentFragment.arguments = bundle
        return contentFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return years.get(position)
    }

}