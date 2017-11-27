package com.leon.coordinatorlayoutexamples

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Leon on 29.10.2017..
 */
class SimplePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {






    override fun getItem(position: Int): Fragment = SimpleFragment()


    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence = "Item $position"
}