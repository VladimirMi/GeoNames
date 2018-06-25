package io.github.vladimirmi.geonames.data.repository

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.maps.model.Tile
import com.google.android.gms.maps.model.TileProvider
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * Tile provider that reads MBTiles format into Google Maps API Android v2
 * (lower zoom levels will be interpolated from higher levels).
 */

const val DEFAULT_WIDTH = 256
const val DEFAULT_HEIGHT = 256

class MBTilesProvider(private val source: File) : TileProvider {

    override fun getTile(x: Int, y: Int, zoom: Int): Tile {
        val bitmap = retrieveBitmap(x, transformY(y, zoom), zoom)

        return if (bitmap == null) {
            TileProvider.NO_TILE
        } else {
            Tile(DEFAULT_WIDTH, DEFAULT_HEIGHT, bitmap)
        }
    }

    private fun retrieveBitmap(x: Int, y: Int, zoom: Int): ByteArray? {
        return if (zoom > 0) {
            readBitmapFromDB(x, y, zoom)
                    ?: crop(retrieveBitmap(x / 2, y / 2, zoom - 1), x, y)
        } else {
            null
        }
    }

    private fun readBitmapFromDB(x: Int, y: Int, zoom: Int): ByteArray? {
        var bitmap: ByteArray? = null

        var db: SQLiteDatabase? = null
        var cursor: Cursor? = null

        try {
            db = SQLiteDatabase.openDatabase(source.path, null, SQLiteDatabase.OPEN_READONLY)

            cursor = db.query("tiles",
                    arrayOf("tile_data"),
                    "tile_column=? and tile_row=? and zoom_level=?",
                    arrayOf("" + x, "" + y, "" + zoom), null, null, null)

            if (cursor != null && cursor.moveToFirst()) {
                bitmap = cursor.getBlob(0)
            }
        } catch (e: SQLiteException) {
            Timber.e(e)
        } finally {
            cursor?.close()
            db?.close()
        }

        return bitmap
    }

    private fun crop(bmp: ByteArray?, x: Int, y: Int): ByteArray? {
        if (bmp == null) return null
        val origin = BitmapFactory.decodeByteArray(bmp, 0, bmp.size)

        val bitmap = Bitmap.createBitmap(
                origin,
                if (x % 2 == 0) 0 else origin.width / 2,
                if (y % 2 == 0) origin.height / 2 else 0,
                origin.width / 2,
                origin.height / 2
        )

        ByteArrayOutputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            bitmap.recycle()
            return it.toByteArray()
        }
    }

    private fun transformY(y: Int, zoom: Int): Int {
        val yMax = 1 shl zoom
        return yMax - y - 1
    }
}
