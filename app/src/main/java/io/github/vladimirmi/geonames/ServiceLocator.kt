package io.github.vladimirmi.geonames

import android.annotation.SuppressLint
import android.content.Context
import io.github.vladimirmi.geonames.data.db.AppDatabase
import io.github.vladimirmi.geonames.data.preference.Preferences
import io.github.vladimirmi.geonames.data.repository.GeoSourcesRepository

/**
 * Created by Vladimir Mikhalev 13.06.2018.
 */

class ServiceLocator(private val appContext: Context) {

    val preferences by lazy { Preferences(appContext) }

    val appDatabase by lazy { AppDatabase.getInstance(appContext) }

    val geoSourcesRepository by lazy {
        GeoSourcesRepository(appDatabase.geoNameDao(), preferences, appContext)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: ServiceLocator
            private set

        fun init(appContext: Context) {
            instance = ServiceLocator(appContext)
        }
    }
}
