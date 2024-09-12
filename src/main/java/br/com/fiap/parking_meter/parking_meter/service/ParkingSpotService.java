package br.com.fiap.parking_meter.parking_meter.service;

import br.com.fiap.parking_meter.parking_meter.dto.ParkingSpotDTO;
import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import br.com.fiap.parking_meter.parking_meter.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository repository;

    public List<ParkingSpot> findAll() {
        return repository.findAll();
    }

    public ParkingSpot save(ParkingSpot spot) {
        return repository.save(spot);
    }

    private ParkingSpotDTO toDTO(ParkingSpot parkingSpot) {
        return new ParkingSpotDTO(
                parkingSpot.getCep(),
                parkingSpot.getLogradouro(),
                parkingSpot.getBairro(),
                parkingSpot.getCidade(),
                parkingSpot.getEstado(),
                parkingSpot.getPais(),
                parkingSpot.getLocation(),
                parkingSpot.getQuantity()
        );
    }

    private ParkingSpot toEntity(ParkingSpotDTO parkingSpotDTO) {
        return new ParkingSpot(
                parkingSpotDTO.cep(),
                parkingSpotDTO.logradouro(),
                parkingSpotDTO.bairro(),
                parkingSpotDTO.cidade(),
                parkingSpotDTO.estado(),
                parkingSpotDTO.pais(),
                parkingSpotDTO.location(),
                parkingSpotDTO.quantity()
        );
    }
}
