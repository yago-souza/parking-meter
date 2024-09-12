package br.com.fiap.parking_meter.parking_meter.controller;

import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import br.com.fiap.parking_meter.parking_meter.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spots")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService service;

    @GetMapping
    public List<ParkingSpot> getAllSpots() {
        return service.findAll();
    }

    @PostMapping
    public ParkingSpot createSpot(@RequestBody ParkingSpot spot) {
        return service.save(spot);
    }
}
