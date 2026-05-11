package com.flight.service;

import com.flight.entity.FlightEntity;
import com.flight.repository.FlightRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FlightService {

    @Inject
    FlightRepository flightRepository;

    public List<FlightEntity> getAllFlights() {
        return flightRepository.listAll();
    }

    public List<FlightEntity> searchFlights(String origin, String destination) {

        return flightRepository.find(
                "origin = ?1 and destination = ?2",
                origin,
                destination
        ).list();
    }
}