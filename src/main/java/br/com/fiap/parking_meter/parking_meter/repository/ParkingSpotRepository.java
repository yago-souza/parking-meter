package br.com.fiap.parking_meter.parking_meter.repository;

import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

    // Método para buscar todos os ParkingSpots por CEP
    List<ParkingSpot> findByCep(String cep);

    Optional<ParkingSpot> findByCepAndLocation(String cep, String location);

    void deleteByCepAndLocation(String cep, String location);
}
