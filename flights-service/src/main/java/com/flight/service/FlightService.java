package com.flight.service;

import com.flight.entity.FlightEntity;
import com.flight.repository.FlightRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class FlightService {

    @Inject
    FlightRepository flightRepository;

    public List<FlightEntity> getAllFlights() {
        return flightRepository.listAll();
    }

    public List<FlightEntity> searchFlights(
            String origin,
            String destination,
            LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return flightRepository.find(
                "origin = ?1 and destination = ?2 and departureTime >= ?3 and departureTime < ?4",
                origin,
                destination,
                start,
                end
        ).list();
    }

    public FlightEntity getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Transactional
    public FlightEntity createFlight(FlightEntity flight) {

        flightRepository.persist(flight);

        return flight;
    }

    @Transactional
    public FlightEntity updateFlight(Long id, FlightEntity updatedFlight) {

        FlightEntity flight = flightRepository.findById(id);

        if (flight == null) {
            return null;
        }

        flight.flightId = updatedFlight.flightId;
        flight.airline = updatedFlight.airline;
        flight.origin = updatedFlight.origin;
        flight.destination = updatedFlight.destination;
        flight.price = updatedFlight.price;
        flight.departureTime = updatedFlight.departureTime;
        flight.arrivalTime = updatedFlight.arrivalTime;

        return flight;
    }

    @Transactional
    public boolean deleteFlight(Long id) {
        return flightRepository.deleteById(id);
    }
}