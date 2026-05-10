package com.travel.service;

import com.travel.dto.FlightDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class FlightService {

    public List<FlightDto> searchFlights(
            String origin,
            String destination,
            BigDecimal maxPrice) {

        FlightDto flight1 = new FlightDto();
        flight1.setFlightId("FL-1001");
        flight1.setAirline("Air France");
        flight1.setOrigin(origin);
        flight1.setDestination(destination);
        flight1.setDepartureTime(LocalDateTime.now().plusDays(10));
        flight1.setArrivalTime(LocalDateTime.now().plusDays(10).plusHours(8));
        flight1.setPrice(BigDecimal.valueOf(650));

        FlightDto flight2 = new FlightDto();
        flight2.setFlightId("FL-1002");
        flight2.setAirline("Lufthansa");
        flight2.setOrigin(origin);
        flight2.setDestination(destination);
        flight2.setDepartureTime(LocalDateTime.now().plusDays(11));
        flight2.setArrivalTime(LocalDateTime.now().plusDays(11).plusHours(7));
        flight2.setPrice(BigDecimal.valueOf(900));

        List<FlightDto> flights = List.of(flight1, flight2);

        if (maxPrice != null) {
            return flights.stream()
                    .filter(flight -> flight.getPrice().compareTo(maxPrice) <= 0)
                    .toList();
        }

        return flights;
    }
}