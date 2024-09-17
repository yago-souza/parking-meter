package br.com.fiap.parking_meter.parking_meter.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingSpot parkingSpot;
    private LocalDateTime purchasedAt;
    private LocalDateTime validUntil;

    public Ticket() {
    }

    public Ticket(Long id, ParkingSpot parkingSpot, LocalDateTime purchaseAt, LocalDateTime validUntil) {
        this.id = id;
        this.parkingSpot = parkingSpot;
        this.purchasedAt = purchaseAt;
        this.validUntil = validUntil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDateTime purchaseAt) {
        this.purchasedAt = purchaseAt;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
