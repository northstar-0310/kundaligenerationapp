package com.example.kundli.controller;

import com.example.kundli.models.BirthDetails;
import com.example.kundli.models.Kundli;
import com.example.kundli.services.KundliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kundli")
public class KundliController {
    @Autowired
    private KundliService kundliService;

    @PostMapping("/generate")
    public Kundli generateKundli(@RequestBody BirthDetails birthDetails) {
        return kundliService.generateKundli(birthDetails);
    }
}

