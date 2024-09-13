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
    public List<ParkingSpot> findByCep(@PathVariable String cep) {
        return parkingSpotService.findByCep(cep);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotDTO> findByCep(@PathVariable Long id) {
        ParkingSpotDTO parkingSpotDTO = parkingSpotService.findById(id);
        return ResponseEntity.ok(parkingSpotDTO);
    }

    @PostMapping
    public ResponseEntity<String> createSpot(@RequestBody ParkingSpotDTO parkingSpotDTO) {
        // Verificar se o ParkingSpot já existe (baseado no CEP e Location)
        Optional<ParkingSpot> existingSpot = parkingSpotService.findByCepAndLocation(
                parkingSpotDTO.cep(),
                parkingSpotDTO.location()
        );
        if (existingSpot.isPresent()) {
            // Se já existe, retornar um erro 409 (Conflito)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Parking spot with the same CEP and Location already exists.");
        }
        // Caso contrário, criar um novo ParkingSpot
        parkingSpotService.save(parkingSpotDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Parking spot created successfully.");
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
