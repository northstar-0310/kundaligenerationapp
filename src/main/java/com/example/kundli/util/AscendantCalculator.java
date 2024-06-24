package com.example.kundli.utils;

public class AscendantCalculator {
    public static String calculateAscendant(double lst, double latitude) {
        double ascendant = (lst + latitude / 15) % 24;
        return getZodiacSign(ascendant);
    }

    private static String getZodiacSign(double degree) {
        String[] signs = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
        int index = (int) (degree / 30);
        return signs[index];
    }
}

