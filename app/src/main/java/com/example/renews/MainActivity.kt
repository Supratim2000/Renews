package com.example.renews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import retrofit2.Retrofit
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val API_KEY: String = "3947c821d3304e5296f1900043f79193"
    private val FRAGMENT_COUNT: Int = 8
    private lateinit var newsPager: ViewPager
    private lateinit var catagoryTl: TabLayout
    private lateinit var newsFragmentList: ArrayList<Fragment>
    private lateinit var newsFragmentNames: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariables()
        addFragmentsInList()
        addFragmentNamesInList()

        catagoryTl.setupWithViewPager(newsPager)
        val newsPagerAdapter: NewsPagerAdapter = NewsPagerAdapter(supportFragmentManager,FRAGMENT_COUNT,newsFragmentList,newsFragmentNames)
        newsPager.adapter = newsPagerAdapter
    }

    private fun addFragmentNamesInList() {
        newsFragmentNames.add("Home")
        newsFragmentNames.add("Business")
        newsFragmentNames.add("Entertainment")
        newsFragmentNames.add("General")
        newsFragmentNames.add("Health")
        newsFragmentNames.add("Science")
        newsFragmentNames.add("Technology")
        newsFragmentNames.add("Sports")
    }

    private fun addFragmentsInList() {
        newsFragmentList.add(HomeFragment())
        newsFragmentList.add(BusinessFragment())
        newsFragmentList.add(EntertainmentFragment())
        newsFragmentList.add(GeneralFragment())
        newsFragmentList.add(HealthFragment())
        newsFragmentList.add(ScienceFragment())
        newsFragmentList.add(TechnologyFragment())
        newsFragmentList.add(SportsFragment())
    }

    private fun initVariables() {
        newsPager = findViewById(R.id.newsPager)
        catagoryTl = findViewById(R.id.catagoryTl)
        newsFragmentList = ArrayList()
        newsFragmentNames = ArrayList()
    }
}