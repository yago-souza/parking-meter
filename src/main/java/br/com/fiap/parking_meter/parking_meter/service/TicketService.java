package br.com.fiap.parking_meter.parking_meter.service;

import br.com.fiap.parking_meter.parking_meter.controller.exception.BusinessException;
import br.com.fiap.parking_meter.parking_meter.controller.exception.ControllerNotFoundException;
import br.com.fiap.parking_meter.parking_meter.dto.TicketDTO;
import br.com.fiap.parking_meter.parking_meter.model.ParkingSpot;
import br.com.fiap.parking_meter.parking_meter.model.Ticket;
import br.com.fiap.parking_meter.parking_meter.repository.ParkingSpotRepository;
import br.com.fiap.parking_meter.parking_meter.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public Ticket createTicket(Long parkingSpotId, int hours) {
        // Verificar se a quantidade de horas está dentro do limite de 3
        if (hours > 3) {
            throw new BusinessException("O ticket não pode ser emitido para mais de 3 horas.");
        }

        // Buscar a vaga correspondente pelo ID
        ParkingSpot parkingSpot = parkingSpotRepository.findById(String.valueOf(parkingSpotId))
                .orElseThrow(() -> new ControllerNotFoundException("Vaga não encontrada"));

        // Verificar a quantidade de tickets válidos (tickets cujo 'validUntil' ainda não passou)
        long ticketsValidos = ticketRepository.countValidTickets(parkingSpotId, LocalDateTime.now());

        if (ticketsValidos >= parkingSpot.getQuantity()) {
            throw new BusinessException("Não há vagas disponíveis no momento.");
        }

        // Criar o novo ticket
        Ticket newTicket = new Ticket();
        newTicket.setParkingSpot(parkingSpot);
        newTicket.setPurchasedAt(LocalDateTime.now());
        newTicket.setValidUntil(LocalDateTime.now().plusHours(hours));

        return ticketRepository.save(newTicket);
    }

    private TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getParkingSpot().getId(),
                ticket.getPurchasedAt(),
                ticket.getValidUntil()
        );
    }
    public Ticket toEntity(TicketDTO ticketDTO) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(String.valueOf(ticketDTO.parkingSpotId()))
                .orElseThrow(() -> new ControllerNotFoundException("Vaga não encontrada"));
        Ticket ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setPurchasedAt(ticketDTO.purchasedAt());
        ticket.setValidUntil(ticketDTO.validUntil());
        return ticket;
    }
}
