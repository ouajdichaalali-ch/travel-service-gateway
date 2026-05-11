package com.flight.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class FlightEntity extends PanacheEntity {

    public String flightId;
    public String airline;
    public String origin;
    public String destination;
    public BigDecimal price;
}