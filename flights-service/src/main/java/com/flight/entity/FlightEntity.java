package com.flight.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flightentity")
public class FlightEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String flightId;

    public String airline;

    public String origin;

    public String destination;

    public BigDecimal price;

    public LocalDateTime departureTime;

    public LocalDateTime arrivalTime;
}