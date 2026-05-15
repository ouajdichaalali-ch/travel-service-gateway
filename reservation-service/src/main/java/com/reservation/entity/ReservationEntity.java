package com.reservation.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "reservationentity")
public class ReservationEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long reservationId;

    public Long customerId;

    public String hotelId;

    public String flightId;

    public String customerName;

    public String customerEmail;
}