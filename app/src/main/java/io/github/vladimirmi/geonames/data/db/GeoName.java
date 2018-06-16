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
        geoname.id = toInt(cols[0]);
        geoname.name = cols[1];
        geoname.asciiName = cols[2];
        geoname.altNames = cols[3];
        geoname.latitude = toFloat(cols[4]);
        geoname.longitude = toFloat(cols[5]);
        geoname.featureClass = cols[6];
        geoname.featureCode = cols[7];
        geoname.countryCode = cols[8];
        geoname.altCC = cols[9];
        geoname.admin1Code = cols[10];
        geoname.admin2Code = cols[11];
        geoname.admin3Code = cols[12];
        geoname.admin4Code = cols[13];
        geoname.population = toInt(cols[14]);
        geoname.elevation = toInt(cols[15]);
        geoname.dem = toInt(cols[16]);
        geoname.timeZone = cols[17];
        geoname.modDate = cols[18];
        return geoname;
    }

    private static int toInt(String s) {
        if (s.isEmpty()) return 0;
        return Integer.parseInt(s);
    }

    private static float toFloat(String s) {
        if (s.isEmpty()) return 0f;
        return Float.parseFloat(s);
    }
}