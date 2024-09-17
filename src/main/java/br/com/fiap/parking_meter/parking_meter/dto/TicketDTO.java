package br.com.fiap.parking_meter.parking_meter.dto;

import java.time.LocalDateTime;

public record TicketDTO(
        Long id,
        Long parkingSpotId,
        LocalDateTime purchasedAt,
        LocalDateTime validUntil) {
}
