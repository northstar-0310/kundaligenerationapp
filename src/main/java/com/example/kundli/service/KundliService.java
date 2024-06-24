package com.example.kundli.services;

import com.example.kundli.models.BirthDetails;
import com.example.kundli.models.Kundli;
import com.example.kundli.utils.AscendantCalculator;
import com.example.kundli.utils.PdfGenerator;
import com.example.kundli.utils.SiderealTimeCalculator;
import com.example.kundli.utils.SwissEphemerisUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class KundliService {
    public ByteArrayInputStream generateKundliPdf(BirthDetails birthDetails) {
        double lst = SiderealTimeCalculator.calculateLST(
                birthDetails.getYear(), birthDetails.getMonth(), birthDetails.getDay(),
                birthDetails.getHour(), birthDetails.getMinute(), 0,
                birthDetails.getLongitude()
        );

        String ascendant = AscendantCalculator.calculateAscendant(lst, birthDetails.getLatitude());
        double sunPosition = SwissEphemerisUtils.getPlanetPosition(SweConst.SE_SUN, birthDetails);

        Kundli kundli = new Kundli();
        kundli.setAscendant(ascendant);
        kundli.setSunPosition(sunPosition);

        // Set other planetary positions

        return PdfGenerator.generateKundliPdf(kundli);
    }

    public com.example.kundli.models.Kundli generateKundli(com.example.kundli.models.BirthDetails birthDetails) {
    }
}
