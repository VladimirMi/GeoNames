package io.github.vladimirmi.geonames.presentation.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.vladimirmi.geonames.presentation.manual.ManualInputFragment
import io.github.vladimirmi.geonames.presentation.map.MapFragment

/**
 * Created by Vladimir Mikhalev 17.06.2018.
 */

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MapFragment()
            1 -> ManualInputFragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getCount() = 2
}
