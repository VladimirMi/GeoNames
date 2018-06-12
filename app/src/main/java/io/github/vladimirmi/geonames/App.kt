package io.github.vladimirmi.geonames

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * Created by Vladimir Mikhalev 09.06.2018.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(applicationContext)
            Timber.plant(Timber.DebugTree())
        }
    }
}