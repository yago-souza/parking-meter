package br.com.fiap.parking_meter.parking_meter.controller;

import br.com.fiap.parking_meter.parking_meter.dto.ParkingSpotDTO;
import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import br.com.fiap.parking_meter.parking_meter.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking-spots")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping
    public List<ParkingSpot> findAllSpots() {
        return parkingSpotService.findAll();
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<ParkingSpotDTO> findByCep(@PathVariable String cep) {
        ParkingSpotDTO parkingSpotDTO = parkingSpotService.findByCep(cep);
        return ResponseEntity.ok(parkingSpotDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotDTO> findById(@PathVariable Long id) {
        ParkingSpotDTO parkingSpotDTO = parkingSpotService.findById(id);
        return ResponseEntity.ok(parkingSpotDTO);
    }

    @PostMapping
    public ResponseEntity<String> createSpot(@RequestBody ParkingSpotDTO parkingSpotDTO) {
        // O serviço lida com a lógica de negócios
        parkingSpotService.createSpot(parkingSpotDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Parking spot created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpotDTO> updateParkingSpot(
            @PathVariable Long id,
            @RequestBody ParkingSpotDTO parkingSpotDTO) {

        ParkingSpotDTO updatedSpot = parkingSpotService.updateParkingSpot(id, parkingSpotDTO);
        return ResponseEntity.ok(updatedSpot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpot(
            @PathVariable Long id) {
        parkingSpotService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
