package com.travel.service;

import com.travel.dto.FlightDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class FlightService {

    public List<FlightDto> searchFlights(String origin, String destination) {

        FlightDto flight = new FlightDto();
        flight.setFlightId("FL-1001");
        flight.setAirline("Air France");
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(LocalDateTime.now().plusDays(10));
        flight.setArrivalTime(LocalDateTime.now().plusDays(10).plusHours(8));
        flight.setPrice(BigDecimal.valueOf(650));

        return List.of(flight);
    }
}