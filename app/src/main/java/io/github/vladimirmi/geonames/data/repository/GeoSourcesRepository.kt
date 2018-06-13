package io.github.vladimirmi.geonames.data.repository

import io.github.vladimirmi.geonames.data.db.GeoName
import io.github.vladimirmi.geonames.data.db.GeoNameDao
import io.github.vladimirmi.geonames.data.preference.Preferences
import okhttp3.*
import timber.log.Timber
import java.io.IOException
import java.util.zip.ZipInputStream

/**
 * Created by Vladimir Mikhalev 12.06.2018.
 */


class GeoSourcesRepository(private val geoNameDao: GeoNameDao,
                           private val preferences: Preferences) {

    init {
        if (!preferences.downloadedSources.contains(GeoSources.name)) loadDb(GeoSources.name)
    }

    private fun loadDb(sourceName: String) {
        Timber.e("loadDb: $sourceName")
        val client = OkHttpClient.Builder().build()

        val request = Request.Builder()
                .url(GeoSources.getUrl(sourceName))
                .build()
        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body: ResponseBody = response.body()!!

                ZipInputStream(body.byteStream()).use { zis ->
                    zis.nextEntry?.let {
                        zis.reader().forEachLine {
                            Timber.e("onResponse: $it")
                            geoNameDao.insert(GeoName.fromString(it))
                        }
                    }
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