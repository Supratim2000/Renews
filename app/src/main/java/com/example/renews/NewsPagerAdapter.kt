package com.example.renews

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class NewsPagerAdapter: FragmentPagerAdapter {
    private val fm: FragmentManager
    private val behavior: Int
    private val newsFragmentList: ArrayList<Fragment>
    private val newsFragmentNames: ArrayList<String>
    constructor(fm: FragmentManager, behavior: Int, newsFragmentList: ArrayList<Fragment>, newsFragmentNames: ArrayList<String>) : super(fm,behavior) {
        this.fm = fm
        this.behavior = behavior
        this.newsFragmentList = newsFragmentList
        this.newsFragmentNames = newsFragmentNames
    }

    override fun getCount(): Int = behavior

    override fun getItem(position: Int): Fragment {
        val currentFragment: Fragment = newsFragmentList[position]
        return currentFragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        val currentFragmentName: String = newsFragmentNames[position]
        return currentFragmentName
    }
}