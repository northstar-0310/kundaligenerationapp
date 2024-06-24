package com.example.kundli.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class SiderealTimeCalculator {
    public static double calculateLST(int year, int month, int day, int hour, int minute, int second, double longitude) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month, day, hour, minute, second);

        double jdUT = calculateJulianDate(calendar);
        double jd0 = Math.floor(jdUT - 0.5) + 0.5;
        double H = (jdUT - jd0) * 24.0;

        double dTT = jdUT - 2451545.0;
        double dUT = jd0 - 2451545.0;

        double T = dTT / 36525.0;
        double GMST = (6.697375 + 0.065709824279 * dUT + 1.0027379 * H + 0.0000258 * T * T) % 24;
        if (GMST < 0) {
            GMST += 24;
        }

        double longitudeInHours = longitude / 15.0;
        double LST = (GMST + longitudeInHours) % 24;
        if (LST < 0) {
            LST += 24;
        }

        return LST;
    }

    private static double calculateJulianDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        if (month <= 2) {
            year -= 1;
            month += 12;
        }

        double A = Math.floor(year / 100.0);
        double B = 2 - A + Math.floor(A / 4.0);

        double jd = Math.floor(365.25 * (year + 4716)) + Math.floor(30.6001 * (month + 1)) + day + B - 1524.5;
        jd += (hour + minute / 60.0 + second / 3600.0) / 24.0;

        return jd;
    }
}
