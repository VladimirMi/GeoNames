package io.github.vladimirmi.geonames.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Vladimir Mikhalev 09.06.2018.
 */

@Dao
interface GeoNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(geoName: List<GeoName>)

    @Query("SELECT * FROM geo_names")
    fun findAll() :List<GeoName>
}