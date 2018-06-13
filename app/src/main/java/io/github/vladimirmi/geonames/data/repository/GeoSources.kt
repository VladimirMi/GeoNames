package io.github.vladimirmi.geonames.data.repository

import java.net.URL

/**
 * Created by Vladimir Mikhalev 13.06.2018.
 */

object GeoSources {

    private const val BASE_URL = "http://download.geonames.org/export/dump"

    const val name = "cities1000.zip"


    fun getUrl(name: String): URL {
        return URL(BASE_URL + name)
    }
}