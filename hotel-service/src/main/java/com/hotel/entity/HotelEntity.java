package com.hotel.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "hotelentity")
public class HotelEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String hotelId;

    public String hotelName;

    public String city;

    public String country;

    public Integer availableRooms;

    public BigDecimal pricePerNight;

    public Double rating;

    public LocalDate availableFrom;

    public LocalDate availableTo;
}