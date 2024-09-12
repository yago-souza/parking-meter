package br.com.fiap.parking_meter.parking_meter.repository;

import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {
}
