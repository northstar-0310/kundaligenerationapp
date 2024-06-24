package com.example.kundli.utils;

import com.example.kundli.models.BirthDetails;
import swisseph.SweConst;
import swisseph.SwissEph;
import swisseph.SweDate;

public class SwissEphemerisUtils {
    public static double getPlanetPosition(int planet, BirthDetails birthDetails) {
        SwissEph sw = new SwissEph();
        double[] xp = new double[6];
        StringBuffer serr = new StringBuffer();
        SweDate sd = new SweDate(birthDetails.getYear(), birthDetails.getMonth() + 1, birthDetails.getDay(),
                birthDetails.getHour() + birthDetails.getMinute() / 60.0);

        sw.swe_calc_ut(sd.getJulDay(), planet, SweConst.SEFLG_SWIEPH, xp, serr);
        return xp[0];
    }
}

