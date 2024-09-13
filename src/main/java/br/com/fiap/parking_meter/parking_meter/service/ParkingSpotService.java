package br.com.fiap.parking_meter.parking_meter.service;

import br.com.fiap.parking_meter.parking_meter.controller.exception.ControllerNotFoundException;
import br.com.fiap.parking_meter.parking_meter.dto.ParkingSpotDTO;
import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import br.com.fiap.parking_meter.parking_meter.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> findAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpotDTO findById(Long id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ControllerNotFoundException("Parking spot not found."));
        return toDTO(parkingSpot);
    }

    public List<ParkingSpot> findByCep(String cep) {
        List<ParkingSpot> spotList = parkingSpotRepository.findByCep(cep);
        if (spotList.isEmpty()) {
            throw new ControllerNotFoundException("CEP: " + cep + " not found.");
        }
        return spotList;
    }

    public Optional<ParkingSpot> findByCepAndLocation(String cep, String location) {
        return parkingSpotRepository.findByCepAndLocation(cep, location);
    }

    public ParkingSpotDTO save(ParkingSpotDTO spotDTO) {
        ParkingSpot parkingSpot = toEntity(spotDTO);
        parkingSpot = parkingSpotRepository.save(parkingSpot);
        ParkingSpotDTO parkingSpotDTO = toDTO(parkingSpot);
        return parkingSpotDTO;
    }

    private ParkingSpotDTO toDTO(ParkingSpot parkingSpot) {
        return new ParkingSpotDTO(
                parkingSpot.getId(),
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
                parkingSpotDTO.id(),
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

    public ParkingSpotDTO updateParkingSpot(Long id, ParkingSpotDTO parkingSpotDTO) {
        ParkingSpot parkingSpotSearch = parkingSpotRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ControllerNotFoundException("Parking spot not found."));
        ParkingSpot existingSpot = parkingSpotSearch;

        // Atualizar os campos do objeto existente com os valores do DTO
        existingSpot.setLogradouro(parkingSpotDTO.logradouro());
        existingSpot.setBairro(parkingSpotDTO.bairro());
        existingSpot.setCidade(parkingSpotDTO.cidade());
        existingSpot.setEstado(parkingSpotDTO.estado());
        existingSpot.setPais(parkingSpotDTO.pais());
        existingSpot.setLocation(parkingSpotDTO.location());
        existingSpot.setQuantity(parkingSpotDTO.quantity());

        // Salvar a entidade atualizada no repositório
        ParkingSpot updatedParkingSpot = parkingSpotRepository.save(existingSpot);

        // Converter a entidade de volta para DTO e retornar
        return toDTO(updatedParkingSpot);
    }

    public void delete(Long id) {
        parkingSpotRepository.deleteById(String.valueOf(id));
    }
}
