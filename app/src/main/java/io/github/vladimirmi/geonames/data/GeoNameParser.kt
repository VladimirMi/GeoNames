package io.github.vladimirmi.geonames.data

import okhttp3.*
import java.io.IOException
import java.util.zip.ZipInputStream

/**
 * Created by Vladimir Mikhalev 09.06.2018.
 */

fun client() {
    val client = OkHttpClient.Builder().build()

    val request = Request.Builder().build()
    val call = client.newCall(request)

    call.enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {

            val body: ResponseBody = response.body()!!

            ZipInputStream(body.byteStream()).use { zis ->
                zis.nextEntry?.let {
                    zis.reader().forEachLine {
                        // todo string to geoName and insert to database
                    }
                }
            }
            body.close()
        }

        override fun onFailure(call: Call, e: IOException) {
            TODO("not implemented")
        }

    })
}