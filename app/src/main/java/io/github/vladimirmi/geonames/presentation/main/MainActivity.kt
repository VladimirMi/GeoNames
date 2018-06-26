package io.github.vladimirmi.geonames.presentation.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.github.vladimirmi.geonames.R
import io.github.vladimirmi.geonames.ServiceLocator
import io.github.vladimirmi.geonames.presentation.export.ExportActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val geoSourcesRepository = ServiceLocator.instance.geoSourcesRepository
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_export) {
            startExportActivity()
            return true
        }
        return false
    }

    private fun startExportActivity() {
        startActivity(Intent(this, ExportActivity::class.java))
    }
}
