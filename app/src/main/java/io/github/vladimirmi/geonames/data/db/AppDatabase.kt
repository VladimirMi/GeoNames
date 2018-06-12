package io.github.vladimirmi.geonames.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Vladimir Mikhalev 12.06.2018.
 */

@Database(entities = [GeoName::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun geoNameDao(): GeoNameDao
}

fun getInstance(context: Context): AppDatabase {
    return Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, "database")
            .build()
}