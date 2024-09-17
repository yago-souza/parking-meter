package br.com.fiap.parking_meter.parking_meter.repository;

import br.com.fiap.parking_meter.parking_meter.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByParkingSpotId(Long parkingSpotId);

    // Conta tickets válidos, ou seja, com validUntil maior que o momento atual
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.parkingSpot.id = :parkingSpotId AND t.validUntil > :currentTime")
    long countValidTickets(@Param("parkingSpotId") Long parkingSpotId, @Param("currentTime") LocalDateTime currentTime);

}
