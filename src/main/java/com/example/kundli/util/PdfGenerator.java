package com.example.kundli.utils;

import com.example.kundli.models.Kundli;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfGenerator {
    public static ByteArrayInputStream generateKundliPdf(Kundli kundli) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
            Paragraph para = new Paragraph("Kundli", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Ascendant: " + kundli.getAscendant(), font));
            document.add(new Paragraph("Sun Position: " + kundli.getSunPosition() + " degrees", font));

            // Add other planetary positions

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

