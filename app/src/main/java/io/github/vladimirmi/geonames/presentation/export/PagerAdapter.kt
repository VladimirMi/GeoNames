package io.github.vladimirmi.geonames.presentation.export

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.vladimirmi.geonames.R
import io.github.vladimirmi.geonames.presentation.manual.ManualExportFragment
import io.github.vladimirmi.geonames.presentation.map.MapExportFragment

/**
 * Created by Vladimir Mikhalev 17.06.2018.
 */

class PagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles: Array<String> = context.resources.getStringArray(R.array.tabs)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ManualExportFragment()
            1 -> MapExportFragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
