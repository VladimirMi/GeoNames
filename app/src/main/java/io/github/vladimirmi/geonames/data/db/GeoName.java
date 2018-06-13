package io.github.vladimirmi.geonames.data.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vladimir Mikhalev 13.06.2018.
 */
@Entity(tableName = "geo_names")
public class GeoName {

    @PrimaryKey
    public int id;
    public String name;
    public String asciiName;
    public String altNames;
    public float latitude;
    public float longitude;
    public String featureClass;
    public String featureCode;
    public String countryCode;
    public String altCC;
    public String admin1Code;
    public String admin2Code;
    public String admin3Code;
    public String admin4Code;
    public int population;
    public int elevation;
    public int dem;
    public String timeZone;
    public String modDate;

    public static GeoName fromString(String string) {
        String[] cols = string.split("\t");
        GeoName geoname = new GeoName();
        geoname.id = Integer.parseInt(cols[0]);
        geoname.name = cols[1];
        geoname.asciiName = cols[2];
        geoname.altNames = cols[4];
        geoname.latitude = Float.parseFloat(cols[5]);
        geoname.longitude = Float.parseFloat(cols[6]);
        geoname.featureClass = cols[7];
        geoname.featureCode = cols[8];
        geoname.countryCode = cols[9];
        geoname.altCC = cols[10];
        geoname.admin1Code = cols[11];
        geoname.admin2Code = cols[12];
        geoname.admin3Code = cols[13];
        geoname.admin4Code = cols[14];
        geoname.population = Integer.parseInt(cols[15]);
        geoname.elevation = Integer.parseInt(cols[16]);
        geoname.dem = Integer.parseInt(cols[17]);
        geoname.timeZone = cols[18];
        geoname.modDate = cols[19];
        return geoname;
    }
}