package io.github.vladimirmi.geonames.presentation.export

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.vladimirmi.geonames.R
import kotlinx.android.synthetic.main.activity_export.*

class ExportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)
        setSupportActionBar(toolbar)

        pager.adapter = PagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    override fun onDestroy() {
        pager.adapter = null
        super.onDestroy()
    }
}
