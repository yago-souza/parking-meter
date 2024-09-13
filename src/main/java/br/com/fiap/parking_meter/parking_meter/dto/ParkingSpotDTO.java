package br.com.fiap.parking_meter.parking_meter.dto;

public record ParkingSpotDTO(
        Long id,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String location,
        int quantity) {
}
