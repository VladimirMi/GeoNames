package io.github.vladimirmi.geonames.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Vladimir Mikhalev 08.06.2018.
 */

@Entity(tableName = "geo_names")
data class GeoName(
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
        val timeZone: String,
        @Ignore val modDate: String
) {
    companion object {

        fun fromString(string: String): GeoName {
            val cols = string.split('\t')
            return GeoName(
                    id = cols[0].toInt(),
                    name = cols[1],
                    asciiName = cols[2],
                    altNames = cols[4],
                    latitude = cols[5].toFloat(),
                    longitude = cols[6].toFloat(),
                    featureClass = cols[7],
                    featureCode = cols[8],
                    countryCode = cols[9],
                    altCC = cols[10],
                    admin1Code = cols[11],
                    admin2Code = cols[12],
                    admin3Code = cols[13],
                    admin4Code = cols[14],
                    population = cols[15].toInt(),
                    elevation = cols[16].toInt(),
                    dem = cols[17].toInt(),
                    timeZone = cols[18],
                    modDate = cols[19]
                    )
        }
    }
}