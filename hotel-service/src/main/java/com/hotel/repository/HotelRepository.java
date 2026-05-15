package com.hotel.repository;

import com.hotel.entity.HotelEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class HotelRepository implements PanacheRepository<HotelEntity> {

    public List<HotelEntity> findByCity(String city) {

        return list(
                "city = ?1",
                city
        );
    }

    public List<HotelEntity> findAvailableHotels(
            String city,
            LocalDate checkIn,
            LocalDate checkOut) {

        return list(
                "city = ?1 and availableFrom <= ?2 and availableTo >= ?3",
                city,
                checkIn,
                checkOut
        );
    }
}