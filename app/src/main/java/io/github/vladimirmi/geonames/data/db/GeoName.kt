package io.github.vladimirmi.geonames.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Vladimir Mikhalev 08.06.2018.
 */

@Entity(tableName = "geo_names")
class GeoName(
        @PrimaryKey val id: Int,
        val name: String,
        @Ignore val asciiName: String,
        val altNames: String,
        val latitude: Float,
        val longitude: Float,
        val featureClass: String,
        val featureCode: String,
        val countryCode: String,
        @Ignore val altCC: String,
        val admin1Code: String,
        val admin2Code: String,
        val admin3Code: String,
        val admin4Code: String,
        val population: Int,
        val elevation: Int,
        val dem: Int,
        val timeZone: String
)