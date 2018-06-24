package io.github.vladimirmi.geonames.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.vladimirmi.geonames.R
import io.github.vladimirmi.geonames.ServiceLocator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val geoSourcesRepository = ServiceLocator.instance.geoSourcesRepository
        pager.adapter = PagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    override fun onDestroy() {
        pager.adapter = null
        super.onDestroy()
    }
}
