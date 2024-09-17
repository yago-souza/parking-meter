package br.com.fiap.parking_meter.parking_meter.controller;

import br.com.fiap.parking_meter.parking_meter.model.Ticket;
import br.com.fiap.parking_meter.parking_meter.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @RequestParam Long parkingSpotId,
            @RequestParam int hours,
            @RequestParam String licensePlate) {

        Ticket newTicket = ticketService.createTicket(parkingSpotId, hours, licensePlate);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTicket);
    }
}
