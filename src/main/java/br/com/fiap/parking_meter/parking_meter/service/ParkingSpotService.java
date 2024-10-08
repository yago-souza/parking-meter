package br.com.fiap.parking_meter.parking_meter.service;

import br.com.fiap.parking_meter.parking_meter.controller.exception.BusinessException;
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

    public ParkingSpotDTO findByCep(String cep) {
        Optional<ParkingSpotDTO> existingSpot = parkingSpotRepository.findByCep(cep);

        // Se não existir, lança a exceção
        ParkingSpotDTO parkingSpotDTO = existingSpot.orElseThrow(() -> new BusinessException("Parking spot not found."));
        return parkingSpotDTO;
    }

    public Optional<ParkingSpot> findByCepAndLocation(String cep, String location) {
        return parkingSpotRepository.findByCepAndLocation(cep, location);
    }

    private ParkingSpotDTO save(ParkingSpotDTO spotDTO) {
        ParkingSpot parkingSpot = toEntity(spotDTO);
        parkingSpot = parkingSpotRepository.save(parkingSpot);
        ParkingSpotDTO parkingSpotDTO = toDTO(parkingSpot);
        return parkingSpotDTO;
    }

    public void createSpot(ParkingSpotDTO parkingSpotDTO) {
        // Verificar se o ParkingSpot já existe (baseado no CEP e Location)
        Optional<ParkingSpotDTO> existingSpot = parkingSpotRepository.findByCep(parkingSpotDTO.cep());
        // Se o estacionamento já existir, lança a exceção BusinessException
        if (existingSpot.isPresent()) {
            throw new BusinessException("Parking spot with the same CEP already exists.");
        }
        // Converter o DTO para entidade e salvar o novo estacionamento
        ParkingSpot parkingSpot = toEntity(parkingSpotDTO);
        parkingSpotRepository.save(parkingSpot);
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
