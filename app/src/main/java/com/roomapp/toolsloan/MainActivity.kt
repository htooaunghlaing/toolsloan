package com.roomapp.toolsloan

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.roomapp.toolsloan.ui.friends.FriendsFragment
import com.roomapp.toolsloan.ui.tools.ToolsFragment

class MainActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    @SuppressLint("LogConditional")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager!!)

        tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ToolsFragment(), getString(R.string.str_tools))
        adapter.addFragment(FriendsFragment(), getString(R.string.str_friends))
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
