package io.github.vladimirmi.geonames.data.preference

import android.content.Context

/**
 * Created by Vladimir Mikhalev 13.06.2018.
 */

class Preferences(context: Context) {

    private val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    var downloadedSources: Set<String> by Preference(prefs, "DOWNLOADED_SOURCES", emptySet())
}
