package io.github.vladimirmi.geonames.data.repository

import android.content.Context
import io.github.vladimirmi.geonames.data.db.GeoName
import io.github.vladimirmi.geonames.data.db.GeoNameDao
import io.github.vladimirmi.geonames.data.preference.Preferences
import okhttp3.*
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.zip.ZipInputStream

/**
 * Created by Vladimir Mikhalev 12.06.2018.
 */


class GeoSourcesRepository(private val geoNameDao: GeoNameDao,
                           private val preferences: Preferences) {

    init {
        if (!preferences.downloadedSources.contains(GeoSources.name)) {
            loadDb(GeoSources.name)
        }
    }

    private fun loadDb(sourceName: String) {
        val client = OkHttpClient.Builder().build()

        val url = GeoSources.getUrl(sourceName)
        val request = Request.Builder()
                .url(url)
                .build()
        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body: ResponseBody = response.body()!!

                ZipInputStream(body.byteStream()).use { zis ->

                    var geonames = ArrayList<GeoName>(1000)
                    zis.reader().forEachLine {
                        if (geonames.size == 1000) {
                            geoNameDao.insert(geonames)
                            geonames = ArrayList(1000)
                        }
                        geonames.add(GeoName.fromString(it))
                    }
                    if (geonames.isNotEmpty()) geoNameDao.insert(geonames)
                }

                body.close()
                preferences.downloadedSources += sourceName
            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented")
            }

        })
    }

}